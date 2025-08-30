package com.atman.aahara.Family;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("OneTimePass")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyQueue {
    @Id
    private String id;

    private String customer;

    private String family;

    @TimeToLive
    private Long ttl;

    private boolean approval;
}
