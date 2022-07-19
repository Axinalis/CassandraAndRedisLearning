package com.axinalis.noSqlDbs.cotroller;

import com.axinalis.noSqlDbs.controller.KeyValueController;
import com.axinalis.noSqlDbs.entity.KeyValuePair;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.repository.KeyValueRepository;
import com.axinalis.noSqlDbs.repository.UserRepository;
import com.axinalis.noSqlDbs.service.KeyValueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KeyValueController.class)
@ExtendWith(SpringExtension.class)
public class KeyValueControllerTest {

    @Autowired
    private KeyValueController keyValueController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private KeyValueService keyValueService;
    @MockBean
    private KeyValueRepository keyValueRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testGettingAllKayValuesPairs() throws Exception{
        when(keyValueService.keyValuesList()).thenReturn(List.of(new KeyValuePair("key1", "value1")));

        mockMvc.perform(get("/library/values"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].keyValue").value("key1"))
                .andExpect(jsonPath("$[0].valueValue").value("value1"));
    }

    @Test
    public void testGettingKeyValuePairByKey() throws Exception {
        when(keyValueService.keyValueByKey("key1")).thenReturn(new KeyValuePair("key1", "value1"));

        mockMvc.perform(get("/library/values/key1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("keyValue").value("key1"))
                .andExpect(jsonPath("valueValue").value("value1"));
    }

    @Test
    public void testCreatingKeyValuePair() throws Exception {
        KeyValuePair pair = newKeyValuePair();
        when(keyValueService.updateKeyValue(pair)).thenReturn(pair);

        mockMvc.perform(post("/library/values")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(pair)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("keyValue").value("key1"))
                .andExpect(jsonPath("valueValue").value("value1"));
    }

    @Test
    public void testUpdatingKeyValuePair() throws Exception {
        KeyValuePair pair = newKeyValuePair();
        when(keyValueService.updateKeyValue(pair)).thenReturn(pair);

        mockMvc.perform(put("/library/values/key1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(pair)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("keyValue").value("key1"))
                .andExpect(jsonPath("valueValue").value("value1"));
    }

    @Test
    public void testDeletingKeyValuePair() throws Exception{
        mockMvc.perform(delete("/library/values/key1"))
                .andExpect(status().isOk());

        verify(keyValueService, times(1)).deleteKeyValue("key1");
    }

    private KeyValuePair newKeyValuePair(){
        return new KeyValuePair("key1", "value1");
    }
}
