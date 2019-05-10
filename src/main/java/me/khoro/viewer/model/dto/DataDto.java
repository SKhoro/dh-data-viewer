package me.khoro.viewer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author s-kh
 * Транспортная сущность, содержащая данные в требуемом формате
 * <i>user_id | fact_id | count() | json array of fact_str</i>
 *
 * @since 0.0.1
 * @version 0.0.1
 */
@Data
@AllArgsConstructor
public class DataDto {

    private String userId;

    private Long factId;

    private Long count;

    private List<String> factStr;
}
