package com.axinalis.noSqlDbs.repository;

import com.axinalis.noSqlDbs.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ClientEntity, Long> {
}
