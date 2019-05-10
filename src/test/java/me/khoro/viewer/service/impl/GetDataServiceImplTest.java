package me.khoro.viewer.service.impl;

import me.khoro.viewer.model.dto.DataDto;
import me.khoro.viewer.model.entity.DbData;
import me.khoro.viewer.repository.DbDataRepository;
import me.khoro.viewer.service.GetDataService;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetDataServiceImplTest {

    @Mock
    private DbDataRepository dbDataRepository;

    @Before
    public void init() {
        Set<DbData> dbDataSet = new HashSet<>();
        dbDataSet.add(new DbData(1L, "user_1", 1L, "qwertyuasdfgzxcvbhd", Timestamp.from(Instant.now())));
        dbDataSet.add(new DbData(2L, "user_1", 1L, "kwertyuasdfgzxcvbhd", Timestamp.from(Instant.now())));
        dbDataSet.add(new DbData(3L, "user_1", 2L, "qwertyuasdfgzxcvbha", Timestamp.from(Instant.now())));
        dbDataSet.add(new DbData(4L, "user_2", 2L, "qwertyuasdfgzxcvbhj", Timestamp.from(Instant.now())));

        when(dbDataRepository.findDistinctByCreateDateBetween(any(), any())).thenReturn(dbDataSet);
    }

    @Test
    public void getDataAndAllOk() {
        GetDataService dataService = new GetDataServiceImpl(dbDataRepository);
        Set<DataDto> dataDtoSet = dataService.getData(0, System.currentTimeMillis());
        assertNotNull(dataDtoSet);
        assertEquals(dataDtoSet.size(), 3L);
    }
}