package com.axinalis.noSqlDbs.service.impl;

import com.axinalis.noSqlDbs.entity.KeyValuePair;
import com.axinalis.noSqlDbs.repository.KeyValueRepository;
import com.axinalis.noSqlDbs.service.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class KeyValueServiceImpl implements KeyValueService {

    @Autowired
    private KeyValueRepository keyValueRepository;

    public KeyValueServiceImpl(){
    }

    public KeyValueServiceImpl(@Autowired KeyValueRepository keyValueRepository) {
        this.keyValueRepository = keyValueRepository;
    }

    @Override
    public List<KeyValuePair> keyValuesList() {
        Iterable<KeyValuePair> iterable = keyValueRepository.findAll();

        return StreamSupport
                .stream(iterable.spliterator(), false).toList();
    }

    @Override
    public KeyValuePair keyValueByKey(String key) {
        KeyValuePair pair = keyValueRepository
                .findById(key)
                .orElseThrow(() -> new RuntimeException("No such keyValue pair found"));
        return pair;
    }

    @Deprecated
    @Override
    public KeyValuePair createKeyValue(KeyValuePair keyValuePair) {
        return updateKeyValue(keyValuePair);
    }

    @Override
    public KeyValuePair updateKeyValue(KeyValuePair keyValuePair) {
        return keyValueRepository.save(keyValuePair);
    }

    @Override
    public void deleteKeyValue(String key) {
        keyValueRepository.deleteById(key);
    }
}
