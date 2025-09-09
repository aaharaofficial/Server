package com.atman.aahara.OneTimePass.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends CrudRepository<OTPEntity, String> {
}
