package me.khoro.viewer.service.impl;

import me.khoro.viewer.exception.InvalidTimestampParamsException;
import me.khoro.viewer.model.dto.DataDto;
import me.khoro.viewer.model.entity.DbData;
import me.khoro.viewer.repository.DbDataRepository;
import me.khoro.viewer.service.GetDataService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author s-kh
 * Реализация {@link me.khoro.viewer.service.GetDataService}
 * {@link #dbDataRepository} - провайдер данных
 *
 * @since 0.0.1
 * @version 0.0.1
 */
@Service
public class GetDataServiceImpl implements GetDataService {

    private final DbDataRepository dbDataRepository;

    public GetDataServiceImpl(DbDataRepository dbDataRepository) {
        this.dbDataRepository = dbDataRepository;
    }

    @Override
    public Set<DataDto> getData(long start, long stop) {
        validateDates(start, stop);
        Timestamp startTimeStamp = new Timestamp(start);
        Timestamp stopTimeStamp = new Timestamp(stop);
        Set<DbData> dataSet = dbDataRepository.findDistinctByCreateDateBetween(startTimeStamp, stopTimeStamp);
        return mapDbDataSetToDataDtoSet(dataSet);
    }

    /**
     * Преобразует множество entity в соответствующее множество dto
     * @param dataSet - entity из базы данных, которые необходимо преобразовать в dto-сущности
     * @return - множество соответствующих dto-сущностей
     */
    private Set<DataDto> mapDbDataSetToDataDtoSet(Set<DbData> dataSet) {
        Map<Pair<String, Long>, List<String>> accumulator = new HashMap<>();
        dataSet.forEach(dbd -> {
            Pair<String, Long> pair = Pair.of(dbd.getUserId(), dbd.getFactId());
            if (accumulator.containsKey(pair)) {
                List<String> factStrings = accumulator.get(pair);
                factStrings.add(dbd.getFactStr());
            } else {
                List<String> factStrings = new ArrayList<>();
                factStrings.add(dbd.getFactStr());
                accumulator.put(pair, factStrings);

            }
        });
        return accumulator.entrySet().stream()
                .map(this::mapDataEntryToDataDto)
                .collect(Collectors.toSet());
    }

    /**
     * Преобразует единствунную Map.Entry в соответвующую ей dto
     * @param entry - входная пара ключ-знаение
     * @return - сформированную dto-сущеность
     */
    private DataDto mapDataEntryToDataDto(Map.Entry<Pair<String, Long>, List<String>> entry) {
        Pair<String, Long> pair = entry.getKey();
        String userId = pair.getFirst();
        Long factId = pair.getSecond();
        List<String> factStrings = entry.getValue();
        long count = factStrings.size();
        return new DataDto(userId, factId, count, factStrings);
    }

    /**
     * Проверяет входные параметры Timestamp на соответствие ограничениям
     * @param start - время "с"
     * @param stop - время "до"
     * @throws InvalidTimestampParamsException в случае обнаружения несоответствия
     */
    private void validateDates(long start, long stop) {
        long now = Instant.now().toEpochMilli();
        if (start < 0 || stop < 0 || start > stop || start > now) {
            throw new InvalidTimestampParamsException(start, start);
        }
    }
}
