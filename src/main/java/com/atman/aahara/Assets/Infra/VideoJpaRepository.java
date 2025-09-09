package com.atman.aahara.Assets.Infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoJpaRepository extends JpaRepository<VideoEntity, UUID> {
}
