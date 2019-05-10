package me.khoro.viewer.repository;

import me.khoro.viewer.model.entity.DbData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author s-kh
 * Стандартный spring data репозиторий для {@link me.khoro.viewer.model.entity.DbData}
 *
 * @since 0.0.1
 * @version 0.0.1
 */
@Repository
public interface DbDataRepository extends JpaRepository<DbData, Long> {

    /**
     * @param start - начало
     * @param stop - окончанеие
     * @return - возвращает множество сущностей удовлетворяющих условию - были созданы между двумя этими датами
     */
    Set<DbData> findDistinctByCreateDateBetween(@Param("start") Timestamp start, @Param("stop") Timestamp stop);
}
