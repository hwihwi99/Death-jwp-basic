package next.jdbcTemplate;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, Object... parameters) throws DataAccessException {

        try (Connection con =  ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);){

            // 가변인자 사용
            for(int i = 0; i<parameters.length; i++) {
                pstmt.setObject(i+1, parameters[i]);
            }

            // pss.setValues(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rw) throws DataAccessException {

        try (Connection con =  ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);){

            pss.setValues(pstmt);
            ResultSet rs =  pstmt.executeQuery();

            List<T> objectList = new ArrayList<T>();
            while (rs.next()) {
                objectList.add(rw.mapRow(rs));
            }

            return objectList;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    public <T> T queryForObject(String sql,PreparedStatementSetter pss, RowMapper<T> rw) throws DataAccessException {
        List<T> result = query(sql,pss,rw);
        if(result.isEmpty()) {
            return null;
        }
        return  result.get(0);
    }



}
