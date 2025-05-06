package  com.calendar.milestone.model.repository;

import com.calendar.milestone.model.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
public class UserRepository{
    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public User select(int id){
        String sql="select * from users where id=?";
        User user=jdbcTemplate.queryForObject(sql,new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet rs,int rowNum)throws SQLException{
                User user =new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPhoto(rs.getString("photo"));
                user.setBirthday(rs.getDate("birthday").toLocalDate());
                user.setEmail(rs.getString("email"));
            return user;
            }
        },id);
        return user;
    }

    public int insert(User user){
        final String sql="insert into users(name,photo,birthday,email) values(?,?,?,?)";
        return jdbcTemplate.update(sql,user.getName(),user.getPhoto(),user.getBirthday(),user.getEmail()); 
    }
    public int update(User user){
        final String sql="update users set name=?,photo=?,birthday=?,email=? where id=?";
        return jdbcTemplate.update(sql,user.getName(),user.getPhoto(),user.getBirthday(),user.getEmail(),user.getId());
    }

    public int delete(int id){
        final LocalDateTime now =LocalDateTime.now();
        final String sql="update users set deleted_at=? where id=?";
        return jdbcTemplate.update(sql,now,id);
    } 


}