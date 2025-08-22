package com.atman.aahara.Cache;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class RedisCacheAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    private String resolveKey(MethodSignature signature, Object[] args, String prefix, String keySpel) {
        try {
            ExpressionParser parser = new SpelExpressionParser();
            StandardEvaluationContext context = new StandardEvaluationContext();

            String[] paramNames = signature.getParameterNames();
            if (paramNames != null) {
                for (int i = 0; i < paramNames.length; i++) {
                    context.setVariable(paramNames[i], args[i]);
                }
            }

            Expression expression = parser.parseExpression(keySpel);
            String evaluatedKey = expression.getValue(context, String.class);
            return prefix + ":" + evaluatedKey;

        } catch (Exception e) {
            System.err.println("SpEL error: " + e.getMessage());
            return prefix + ":__fallback__";
        }
    }

    @Around("@annotation(cacheable)")
    public Object handleCacheable(ProceedingJoinPoint joinPoint, CacheableRedis cacheable) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String key = resolveKey(signature, joinPoint.getArgs(), cacheable.prefix(), cacheable.key());

        try {
            Object cached = redisTemplate.opsForValue().get(key);
            if (cached != null) return cached;

            Object result = joinPoint.proceed();
            if (result != null) {
                redisTemplate.opsForValue().set(key, result, cacheable.ttlMinutes(), TimeUnit.MINUTES);
            }
            return result;

        } catch (Exception e) {
            System.err.println("CacheableRedis error: " + e.getMessage());
            return joinPoint.proceed();
        }
    }

    @Around("@annotation(cachePut)")
    public Object handleCachePut(ProceedingJoinPoint joinPoint, CachePutRedis cachePut) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String key = resolveKey(signature, joinPoint.getArgs(), cachePut.prefix(), cachePut.key());

        try {
            Object result = joinPoint.proceed();
            if (result != null) {
                redisTemplate.opsForValue().set(key, result, cachePut.ttlMinutes(), TimeUnit.MINUTES);
            }
            return result;

        } catch (Exception e) {
            System.err.println("CachePutRedis error: " + e.getMessage());
            return joinPoint.proceed();
        }
    }

    @Around("@annotation(cacheEvict)")
    public Object handleCacheEvict(ProceedingJoinPoint joinPoint, CacheEvictRedis cacheEvict) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String key = resolveKey(signature, joinPoint.getArgs(), cacheEvict.prefix(), cacheEvict.key());

        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            System.err.println("CacheEvictRedis error: " + e.getMessage());
        }

        return joinPoint.proceed();
    }
}
