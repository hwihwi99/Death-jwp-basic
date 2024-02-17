package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.jdbcTemplate.JdbcTemplate;
import next.jdbcTemplate.PreparedStatementSetter;
import next.jdbcTemplate.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate insertJdbcTemplate = new JdbcTemplate();

//        PreparedStatementSetter pss = new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement pstmt) throws SQLException {
//                pstmt.setString(1, user.getUserId());
//                pstmt.setString(2, user.getPassword());
//                pstmt.setString(3, user.getName());
//                pstmt.setString(4, user.getEmail());
//            }
//        };
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        insertJdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }


    public void update(User user) {
        JdbcTemplate updateJdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
        updateJdbcTemplate.update(sql,pss);
    }


    public User findByUserId(String userId) {

        System.out.println(userId);
        JdbcTemplate selectJdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };

        RowMapper<User> rw = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";

        return selectJdbcTemplate.queryForObject(sql, pss, rw);
    }

    public List<User> findAll() {

        JdbcTemplate findAllTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
            }
        };
        RowMapper<User> rw = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS";
        return findAllTemplate.query(sql,pss,rw);
    }

}
