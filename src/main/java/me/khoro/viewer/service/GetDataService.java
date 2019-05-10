package me.khoro.viewer.service;

import me.khoro.viewer.model.dto.DataDto;

import java.util.Set;

/**
 * @author s-kh
 * Интерфейс, определяюищий сервис доступа к данным
 *
 * @since 0.0.1
 * @version 0.0.1
 */
public interface GetDataService {

    /**
     * @param start - время в миллисекундах от 1970 года с которого производить выборку данных
     * @param stop - время в миллисекундах от 1970 года до которого производить выборку данных
     * @return набор данных в формате {@link me.khoro.viewer.model.dto.DataDto}
     */
    Set<DataDto> getData(long start, long stop);
}
