package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * packageName    : com.example.fastcampusmysql.domain.member.repository
 * fileName       : MemberRepository
 * author         : LeeSeongJun
 * date           : 2023-01-16
 * description    : TODO : 향 후에 JPA로 변경해보기
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-16        LeeSeongJun       최초 생성
 */
@RequiredArgsConstructor
@Repository
public class MemberRepository {

    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final private String TABLE = "member";
    /*
    * Optional의 용도
    * : NPE(Null Point Exception) 방지 (null이 올수있는 값을 감싸는 Wrapper 클래스)
    * : 메소드의 결과가 null이 될 수 있으며,
    *    null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용되어야 한다.
    *    그 이유는 오버헤드가 있으므로 시스템 성능이 저하된다.
     * */
    public Optional<Member> findById(Long id) {
        /*
        * select *
        * from Member
        * where id = : id
        * */
        var sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        var param = new MapSqlParameterSource().addValue("id", id);

        RowMapper<Member> rowMapper = (ResultSet resultSet, int rowNum) -> Member
                .builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .nickname(resultSet.getString("nickname"))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                .build();

        var member = namedParameterJdbcTemplate.queryForObject(sql, param, rowMapper);
        return Optional.ofNullable(member);
    }
    public Member save(Member member) {
        /*
            member id를 보고 갱신 또는 삽입을 정함
                반환값은 id를 담아서 반환한다.
        */
        if (member.getId() == null) {
            return insert(member);
        }
        return update(member);
//        return Member.builder().build();
    }

    private Member insert(Member member) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        var id =  simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Member.builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .build();
    }

    private Member update(Member member) {
        // TODO : implements
        return member;
    }
}
