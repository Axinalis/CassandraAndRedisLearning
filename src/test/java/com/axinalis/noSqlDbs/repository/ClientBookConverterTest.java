package com.axinalis.noSqlDbs.repository;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientBookConverterTest {

    private UserBooksConverter converter = new UserBooksConverter();

    @Test
    public void testParsingListToString(){
        List<Long> list1 = Arrays.asList(1L, 2L, 3L, 4L);
        List<Long> list2 = Arrays.asList(30000L, 232422L, 585947L);
        List<Long> list3 = Arrays.asList(2L);
        List<Long> list4 = Arrays.asList(4L, 3L, 2L, 1L);

        assertEquals("1;2;3;4", converter.convertToDatabaseColumn(list1));
        assertEquals("30000;232422;585947", converter.convertToDatabaseColumn(list2));
        assertEquals("2", converter.convertToDatabaseColumn(list3));
        assertEquals("4;3;2;1", converter.convertToDatabaseColumn(list4));
    }

    @Test
    public void testParsingStringToList(){
        String str1 = "1;2;3;4";
        String str2 = "30000;232422;585947";
        String str3 = "2";
        String str4 = "4;3;2;1";

        assertEquals(Arrays.asList(1L, 2L, 3L, 4L), converter.convertToEntityAttribute(str1));
        assertEquals(Arrays.asList(30000L, 232422L, 585947L), converter.convertToEntityAttribute(str2));
        assertEquals(Arrays.asList(2L), converter.convertToEntityAttribute(str3));
        assertEquals(Arrays.asList(4L, 3L, 2L, 1L), converter.convertToEntityAttribute(str4));
    }
}
