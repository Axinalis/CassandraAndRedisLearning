package com.axinalis.noSqlDbs.repository;

import com.axinalis.noSqlDbs.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
