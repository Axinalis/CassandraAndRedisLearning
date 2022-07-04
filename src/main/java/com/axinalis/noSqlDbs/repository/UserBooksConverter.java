package com.axinalis.noSqlDbs.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class UserBooksConverter implements AttributeConverter<List<Long>, String> {

    private final static String DELIMITER = ";";
    private static Logger log = LoggerFactory.getLogger(UserBooksConverter.class);

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        int lastElement = attribute.size() - 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lastElement; i++) {
            builder.append(attribute.get(i));
            builder.append(DELIMITER);
        }
        builder.append(attribute.get(lastElement));
        return builder.toString();
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        List<Long> books = new ArrayList<>(dbData.split(DELIMITER).length);
        for (String bookId : dbData.split(DELIMITER)) {
            try {
                books.add(Long.valueOf(bookId));
            } catch (NumberFormatException exception) {
                log.info("Error while parsing book id from database. Book id was {}. Exception: {}", bookId, exception);
            }
        }
        return books;
    }
}
