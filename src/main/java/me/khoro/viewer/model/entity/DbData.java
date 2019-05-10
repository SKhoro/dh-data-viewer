package me.khoro.viewer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author s-kh
 * Сущность JPA, представляющая таблицу 'gen_data'
 *
 * @since 0.0.1
 * @version 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gen_data")
public class DbData {

    @Id
    @GeneratedValue(generator = "gen_data_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "fact_id", nullable = false)
    private Long factId;

    @Column(name = "fact_str", nullable = false)
    private String factStr;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
}
