package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.entity.KeyValuePair;

import java.util.List;

public interface KeyValueService {

    List<KeyValuePair> keyValuesList();
    KeyValuePair keyValueByKey(String key);
    KeyValuePair createKeyValue(KeyValuePair keyValuePair);
    KeyValuePair updateKeyValue(KeyValuePair keyValuePair);
    void deleteKeyValue(String key);

}
