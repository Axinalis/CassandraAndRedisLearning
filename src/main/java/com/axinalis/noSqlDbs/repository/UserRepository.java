package com.axinalis.noSqlDbs.repository;

import com.axinalis.noSqlDbs.entity.ClientEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<ClientEntity, Long> {
}
