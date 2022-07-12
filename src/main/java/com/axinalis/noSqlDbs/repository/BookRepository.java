package com.axinalis.noSqlDbs.repository;

import com.axinalis.noSqlDbs.entity.BookEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface BookRepository extends CassandraRepository<BookEntity, Long> {
}
