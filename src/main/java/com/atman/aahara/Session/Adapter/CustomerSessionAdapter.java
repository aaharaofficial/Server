package com.atman.aahara.Session.Adapter;

import com.atman.aahara.Session.Port.CustomerSessionPort;
import com.atman.aahara.Session.Domain.CustomerSession;
import com.atman.aahara.Session.Infra.CustomerSessionEntity;
import com.atman.aahara.Session.Infra.CustomerSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class CustomerSessionAdapter implements CustomerSessionPort {

    private final CustomerSessionRepository repository;
    private final CustomerSessionMapper mapper;

    @Override
    public CustomerSession save(CustomerSession session) {
        CustomerSessionEntity entity = mapper.toEntity(session);
        CustomerSessionEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<CustomerSession> findByRefreshToken(String refreshToken) {
        return repository.findByRefreshToken(refreshToken)
                .map(mapper::toDomain);
    }

    @Override
    public List<CustomerSession> findByMobileNumber(String mobileNumber) {
        return repository.findByMobileNumber(mobileNumber)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<CustomerSession> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
