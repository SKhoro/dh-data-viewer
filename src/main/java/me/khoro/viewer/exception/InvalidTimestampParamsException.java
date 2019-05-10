package me.khoro.viewer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, генерироемое при валидации входных параметров
 * Статус изменен с 403 на 400, как более подходящий по назначению
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidTimestampParamsException extends RuntimeException {

    public InvalidTimestampParamsException(long start, long stop) {
        super(String.format("Invalid start/stop parameters. They must be not less then 0, stop date mast be after " +
                "start data and start date must be after now, but actually got %s as start and %s as stop", start, stop));
    }

}
