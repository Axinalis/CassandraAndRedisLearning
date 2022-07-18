package com.axinalis.noSqlDbs.controller;

import com.axinalis.noSqlDbs.entity.KeyValuePair;
import com.axinalis.noSqlDbs.service.KeyValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/values")
public class KeyValueController {

    @Autowired
    private KeyValueService keyValueService;
    private Logger log = LoggerFactory.getLogger(KeyValueController.class);

    @GetMapping
    public List<KeyValuePair> getKeyValuesList(){
        log.info("List of all keyValues requested");
        return keyValueService.keyValuesList();
    }

    @GetMapping("{key}")
    public KeyValuePair getKeyValueByKey(@PathVariable String key){
        log.info("KeyValue with ket {} was requested", key);
        return keyValueService.keyValueByKey(key);
    }

    @PostMapping
    public KeyValuePair createKeyValue(@RequestBody KeyValuePair pair){
        log.info("Creating of new keyValue was requested. {}", pair);
        return keyValueService.updateKeyValue(pair);
    }

    @PutMapping("{key}")
    public KeyValuePair updateKeyValue(@PathVariable String key, @RequestBody KeyValuePair pair){
        pair.setKeyValue(key);
        log.info("Updating of keyValue was requested. {}", pair);
        return keyValueService.updateKeyValue(pair);
    }

    @DeleteMapping("{key}")
    public void deleteKeyValueByKey(@PathVariable String key){
        log.info("Deleting of keyValue with key {} was requested", key);
        keyValueService.deleteKeyValue(key);
    }

}
