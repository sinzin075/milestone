package com.calendar.milestone.model.repository;


import com.calendar.milestone.model.entity.Goal;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class GoalRepository{
    
    private JdbcTemplate jdbcTemplate;
    public GoalRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    public Goal select(int id){
        final String sql="select * from goals where id=?";
        Goal goal=jdbcTemplate.queryForObject(sql,new RowMapper<Goal>(){
            @Override
            public Goal mapRow(ResultSet rs,int rowNum)throws SQLException{
                Goal goalMap= new Goal();
                goalMap.setId(rs.getInt("id"));
                goalMap.setUser_id(rs.getInt("user_id"));
                goalMap.setTitle(rs.getString("title"));
                goalMap.setContent(rs.getString("content"));
                goalMap.setDate(rs.getDate("date").toLocalDate());
                Timestamp timestamp_created = rs.getTimestamp("created_at");
                Timestamp timestamp_updated = rs.getTimestamp("updated_at");
                Timestamp timestamp_deleted = rs.getTimestamp("deleted_at");
                if(timestamp_created!=null){goalMap.setCreated_at(timestamp_created.toLocalDateTime());}
                if(timestamp_updated!=null){goalMap.setUpdated_at(timestamp_updated.toLocalDateTime());}
                if(timestamp_deleted!=null){goalMap.setDeleted_at(timestamp_deleted.toLocalDateTime());}
                return goalMap;
            }
        },id);
        return goal;
    } 
    
    public int insert(Goal goal){
        final String sql = "insert into goals(user_id,title,content,date) values(?,?,?,?)";
        return jdbcTemplate.update(sql,goal.getUser_id(),goal.getTitle(),goal.getContent(),goal.getDate());
    }
    
    public int update(Goal goal){
        final String sql ="update goals set title=?,content=?,date=? where id=?";
        return jdbcTemplate.update(sql,goal.getTitle(),goal.getContent(),goal.getDate(),goal.getId());
    }

    public int delete(int id){
        final String sql="update goals set deleted_at=? where id=?";
        return jdbcTemplate.update(sql,LocalDateTime.now(),id);
    }
}