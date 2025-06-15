package com.calendar.milestone.model.repository;


import com.calendar.milestone.model.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User select(int id) {
        String sql = "select * from users where id=?";
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User userMap = new User();
                userMap.setId(rs.getInt("id"));
                userMap.setName(rs.getString("name"));
                userMap.setPhoto(rs.getString("photo"));
                if (rs.getDate("birthday") != null) {
                    userMap.setBirthday(rs.getDate("birthday").toLocalDate());
                }
                userMap.setEmail(rs.getString("email"));
                Timestamp timestampCreated = rs.getTimestamp("created_at");
                Timestamp timestampUpdated = rs.getTimestamp("updated_at");
                Timestamp timestampDeleted = rs.getTimestamp("deleted_at");
                if (timestampCreated != null) {
                    userMap.setCreatedAt(timestampCreated.toLocalDateTime());
                }
                if (timestampUpdated != null) {
                    userMap.setUpdatedAt(timestampUpdated.toLocalDateTime());
                }
                if (timestampDeleted != null) {
                    userMap.setDeletedAt(timestampDeleted.toLocalDateTime());
                }
                return userMap;
            }
        }, id);
        return user;
    }

    public int insert(User user) {
        final String sql = "insert into users(name,photo,birthday,email) values(?,?,?,?)";
        return jdbcTemplate.update(sql, user.getName(), user.getPhoto(), user.getBirthday(),
                user.getEmail());
    }

    public int update(User user) {
        final String sql =
                "update users set name=?,photo=?,birthday=?,email=?,password=? where id=?";
        return jdbcTemplate.update(sql, user.getName(), user.getPhoto(), user.getBirthday(),
                user.getEmail(), user.getPassword().getEncordedPassword(), user.getId());
    }

    public int delete(int id) {
        final String sql = "update users set deleted_at=? where id=?";
        return jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }


}
