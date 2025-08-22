package com.atman.aahara.OneTimePass;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OneTimePassRepository extends CrudRepository<OneTimePass,String> {
}
