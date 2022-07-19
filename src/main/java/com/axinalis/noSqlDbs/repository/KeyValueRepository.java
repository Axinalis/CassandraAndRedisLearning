package com.axinalis.noSqlDbs.repository;

import com.axinalis.noSqlDbs.entity.KeyValuePair;
import org.springframework.data.repository.CrudRepository;

public interface KeyValueRepository extends CrudRepository<KeyValuePair, String> {
}
