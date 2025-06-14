package com.calendar.milestone.model.repository;

import com.calendar.milestone.model.entity.Milestone;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class MilestoneRepository {
    private JdbcTemplate jdbcTemplate;

    MilestoneRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Milestone select(int id) {
        String sql = "select * from milestone where id=?";
        Milestone milestone = jdbcTemplate.queryForObject(sql, new RowMapper<Milestone>() {
            public Milestone mapRow(ResultSet rs, int rouNum) throws SQLException {
                Milestone existMilestone = new Milestone();
                existMilestone.setId(rs.getInt("id"));
                existMilestone.setGoalId(rs.getInt("goal_id"));
                existMilestone.setTitle(rs.getString("title"));
                existMilestone.setContent(rs.getString("content"));
                existMilestone.setDate(rs.getDate("date").toLocalDate());
                Timestamp timestampCreated = rs.getTimestamp("created_at");
                Timestamp timestampUpdated = rs.getTimestamp("updated_at");
                Timestamp timestampDeleted = rs.getTimestamp("deleted_at");
                if (timestampCreated != null) {
                    existMilestone.setCreatedAt(timestampCreated.toLocalDateTime());
                }
                if (timestampUpdated != null) {
                    existMilestone.setUpdatedAt(timestampUpdated.toLocalDateTime());
                }
                if (timestampDeleted != null) {
                    existMilestone.setDeletedAt(timestampDeleted.toLocalDateTime());
                }
                return existMilestone;
            }
        }, id);
        return milestone;
    }

    public int insert(Milestone milestone) {
        String sql = "insert into milestone(goal_id,title,content,date) values(?,?,?,?)";
        return jdbcTemplate.update(sql, milestone.getGoalId(), milestone.getTitle(),
                milestone.getContent(), milestone.getDate());
    }

    public int update(Milestone milestone) {
        String sql = "update milestone set title=?,content=?,date=? where id=?";
        return jdbcTemplate.update(sql, milestone.getTitle(), milestone.getContent(),
                milestone.getDate(), milestone.getId());
    }

    public int delete(int id) {
        String sql = "update milestone set deleted_at=? where id=?";
        return jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }
}
