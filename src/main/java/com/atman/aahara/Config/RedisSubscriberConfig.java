package com.atman.aahara.Config;


import com.atman.aahara.WebSocket.PlannerPubSubSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@RequiredArgsConstructor
public class RedisSubscriberConfig {
    private final RedisConnectionFactory connectionFactory;
    private final PlannerPubSubSubscriber subscriber;

    public RedisMessageListenerContainer container() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(subscriber, new PatternTopic("family:*:status"));
        return container;
    }
}
