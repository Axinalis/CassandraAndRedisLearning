package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.entity.KeyValuePair;
import com.axinalis.noSqlDbs.repository.KeyValueRepository;
import com.axinalis.noSqlDbs.service.impl.KeyValueServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KeyValueServiceTest {

    private KeyValueServiceImpl keyValueService;
    @Mock
    private KeyValueRepository repository;

    @BeforeEach
    public void setup(){
        keyValueService = new KeyValueServiceImpl(repository);
    }

    @Test
    public void testGettingAllKeyValuePairs(){
        KeyValuePair pair = newKeyValuePair();
        when(repository.findAll()).thenReturn(List.of(pair));

        assertEquals(1, keyValueService.keyValuesList().size());
        assertEquals("key1", keyValueService.keyValuesList().get(0).getKeyValue());
        assertEquals("value1", keyValueService.keyValuesList().get(0).getValueValue());

    }

    @Test
    public void testGettingKeyValuePairByKey(){
        KeyValuePair pair = newKeyValuePair();
        when(repository.findById("key1")).thenReturn(Optional.of(pair));
        when(repository.findById("key2")).thenReturn(Optional.empty());

        assertEquals(pair, keyValueService.keyValueByKey("key1"));
        assertThrows(RuntimeException.class, () -> {
            keyValueService.keyValueByKey("key2");
        });
    }

    @Test
    public void testUpdatingKeyValuePair(){
        KeyValuePair pair = newKeyValuePair();
        when(repository.save(pair)).thenReturn(pair);

        assertEquals(pair, keyValueService.updateKeyValue(pair));
        verify(repository, times(1)).save(pair);
    }

    @Test
    public void testDeletingKeyValuePair(){
        keyValueService.deleteKeyValue("key1");

        verify(repository, times(1)).deleteById("key1");
    }

    private KeyValuePair newKeyValuePair(){
        return new KeyValuePair("key1", "value1");
    }
}
