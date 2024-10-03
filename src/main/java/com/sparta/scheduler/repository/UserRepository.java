package com.sparta.scheduler.repository;

import com.sparta.scheduler.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User selectUserId(String user_email) {
        String sql = "select * from user where user_email = ?";
        User user = new User();
        return jdbcTemplate.query(sql,resultSet->{
            if(resultSet.next()){
                user.setUser_id(resultSet.getLong("user_id"));
                return user;
            }
            else{
                user.setUser_id(Long.valueOf(0));
                return user;
            }
        },user_email);
    }

    public User insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "insert into user (user_email,username) values (?,?)";
        jdbcTemplate.update(con ->{
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.getUser_email());
            ps.setString(2,user.getUsername());
            return ps;
        },keyHolder);
        user.setUser_id(keyHolder.getKey().longValue());
        return user;
    }
}
