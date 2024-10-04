package com.sparta.scheduler.repository;

import com.sparta.scheduler.entity.scheduler.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class SchedulerRepository {
    private final JdbcTemplate jdbcTemplate;

    public SchedulerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Scheduler insert(Scheduler scheduler) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedules(user_id,username,password,title,content,start_date,end_date) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, scheduler.getUser_id());
            ps.setString(2, scheduler.getUsername());
            ps.setString(3, scheduler.getPassword());
            ps.setString(4, scheduler.getTitle());
            ps.setString(5, scheduler.getContent());
            ps.setString(6, scheduler.getStart_date());
            ps.setString(7, scheduler.getEnd_date());
            return ps;
        },keyHolder);

        scheduler.setSchedule_id(keyHolder.getKey().longValue());
        return scheduler;
    }

    public List<Scheduler> selectAll() {
        String sql = "SELECT * FROM schedules order by schedule_id desc";

        return jdbcTemplate.query(sql, new RowMapper<Scheduler>() {
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        });
    }

    public Scheduler findById(Long schedule_id) {
        String sql = "SELECT * FROM schedules WHERE schedule_id=?";
        return jdbcTemplate.query(sql, resultSet->{
            if(resultSet.next()) {
                return new Scheduler(resultSet);
            }
            else{
                return null;
            }
        }, schedule_id);
    }

    public Long delete(Long schedule_id) {
        String query = "DELETE FROM schedules WHERE schedule_id = ?";
        jdbcTemplate.update(query, schedule_id);
        return schedule_id;
    }

    public Long checkPassword(Long schedule_id, String password){
        Connection connection;
        PreparedStatement ps;
        String query = "SELECT * FROM schedules WHERE schedule_id = ? AND password = ?";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheduler","root","dksdkffiwna0~!");
            ps = connection.prepareStatement(query);
            ps.setLong(1, schedule_id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getLong("schedule_id");
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("checkPassword - 런타임 에러");
        }
    }

    public Long update(Long schedule_id, Scheduler scheduler) {
        String query = "update schedules set username = ?,title = ?, content  = ?, start_date = ?, end_date = ? where schedule_id = ?";
        jdbcTemplate.update(query, scheduler.getUsername(),scheduler.getTitle(), scheduler.getContent(), scheduler.getStart_date(), scheduler.getEnd_date(),schedule_id);
        return schedule_id;
    }

    public List<Scheduler> selectSearchModifyDate(String date, int pageNum) {
        String query = "select * from schedules where DATE_FORMAT(modification_date,'%Y-%m-%d') = ? order by modification_date desc limit ? , 10";
        return jdbcTemplate.query(query,new RowMapper<Scheduler>(){
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        },date,pageNum);
    }

    public List<Scheduler> selectSearchModifyDateLength(String date) {
        String query = "select * from schedules where DATE_FORMAT(modification_date,'%Y-%m-%d') = ? order by modification_date desc";
        return jdbcTemplate.query(query,new RowMapper<Scheduler>(){
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        },date);
    }


    public List<Scheduler> selectSearchAuthor(String username, int pageNum) {
        String query = "select * from schedules where username = ?  order by modification_date desc limit ? , 10";
        return jdbcTemplate.query(query,new RowMapper<Scheduler>(){
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        },username,pageNum);
    }

    public List<Scheduler> selectSearchAuthorLength(String username) {
        String query = "select * from schedules where username = ?  order by modification_date desc";
        return jdbcTemplate.query(query,new RowMapper<Scheduler>(){
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        },username);
    }

    public List<Scheduler> selectSearchAll(String username, String date, int pageNum) {
        String query = "select * from schedules where username = ? and DATE_FORMAT(modification_date,'%Y-%m-%d') = ? order by  modification_date desc limit ?,?";
        return jdbcTemplate.query(query,new RowMapper<Scheduler>(){
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        },username,date,pageNum,10);
    }
    public List<Scheduler> selectSearchAllLength(String username, String date) {
        String query = "select * from schedules where username = ? and DATE_FORMAT(modification_date,'%Y-%m-%d') = ? order by  modification_date desc";
        return jdbcTemplate.query(query, new RowMapper<Scheduler>() {
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        }, username, date);
    }


    public List<Scheduler> selectAllByPaging(int pageNum) {
        String query = "select * from schedules order by modification_date desc limit ?,? ";
        return jdbcTemplate.query(query, new RowMapper<Scheduler>(){
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        },pageNum,10);
    }

    public List<Scheduler> selectByMySchedule(Long user_id, int pageNum) {
        String query = "select * from schedules where user_id = ? order by modification_date desc limit ?,10";
        return jdbcTemplate.query(query, new RowMapper<Scheduler>(){
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        }, user_id,pageNum);
    }

    public List<Scheduler> selectByMyScheduleCnt(Long user_id) {
        String query = "select * from schedules where user_id = ? order by modification_date desc";
        return jdbcTemplate.query(query, new RowMapper<Scheduler>(){
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(rs);
            }
        }, user_id);
    }
}
