package com.calendar.milestone.model.repository;


import com.calendar.milestone.model.entity.User;
import com.calendar.milestone.model.value.Email;
import com.calendar.milestone.model.value.Password;
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
                userMap.setEmail(Email.of(rs.getString("email")));
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

    public String findPassword(Email email) {
        final String sql = "select password from users where email=?";
        String password = jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String passwordMap = rs.getString("password");
                return passwordMap;
            }
        }, email.getValue());
        return password;
    }

    public int insert(User user) {
        final String sql =
                "insert into users(name,photo,birthday,email,password) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, user.getName(), user.getPhoto(), user.getBirthday(),
                user.getEmailObject().getValue(), user.getPassword().getEncodedPassword());
    }

    public int update(User user) {
        final String sql =
                "update users set name=?,photo=?,birthday=?,email=?,password=? where id=?";
        return jdbcTemplate.update(sql, user.getName(), user.getPhoto(), user.getBirthday(),
                user.getEmailObject().getValue(), user.getPassword().getEncodedPassword(),
                user.getId());
    }

    public int updateEmail(final int id, final Email email) {
        final String sql = "update users set email=? where id=?";
        return jdbcTemplate.update(sql, email.getValue(), id);
    }

    public int updatePassword(final int id, final Password password) {
        final String sql = "update users set password=? where id=?";
        return jdbcTemplate.update(sql, password.getEncodedPassword(), id);
    }

    public int delete(int id) {
        final String sql = "update users set deleted_at=? where id=?";
        return jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }


}
