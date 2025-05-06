package  com.calendar.milestone.model.repository;


import com.calendar.milestone.model.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                User userMap =new User();
                userMap.setId(rs.getInt("id"));
                userMap.setName(rs.getString("name"));
                userMap.setPhoto(rs.getString("photo"));
                userMap.setBirthday(rs.getDate("birthday").toLocalDate());
                userMap.setEmail(rs.getString("email"));
                Timestamp timestamp_created = rs.getTimestamp("created_at");
                Timestamp timestamp_updated = rs.getTimestamp("updated_at");
                Timestamp timestamp_deleted = rs.getTimestamp("deleted_at");
                if(timestamp_created!=null){userMap.setCreated_at(timestamp_created.toLocalDateTime());}
                if(timestamp_updated!=null){userMap.setUpdated_at(timestamp_updated.toLocalDateTime());}
                if(timestamp_deleted!=null){userMap.setDeleted_at(timestamp_deleted.toLocalDateTime());}
            return userMap;
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
        final String sql="update users set deleted_at=? where id=?";
        return jdbcTemplate.update(sql,LocalDateTime.now(),id);
    } 


}