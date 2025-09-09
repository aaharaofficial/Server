package com.atman.aahara.OneTimePass.Adapters;
import com.atman.aahara.OneTimePass.Domain.OTPSession;
import com.atman.aahara.OneTimePass.Ports.OTPStorePort;
import com.atman.aahara.OneTimePass.infrastructure.OTPEntity;
import com.atman.aahara.OneTimePass.infrastructure.OTPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisOTPStoreAdapter implements OTPStorePort {

    private final OTPRepository repository;
    private final OTPMapper mapper;

    @Override
    public OTPSession save(OTPSession session) {
        OTPEntity entity = mapper.toEntity(session);
        OTPEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public OTPSession find(String identifier) {
        return repository.findById(identifier)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public void delete(String identifier) {
        repository.deleteById(identifier);
    }
}
