//package spring.rowmapper;
//
//import org.springframework.jdbc.core.RowMapper;
//import spring.model.User;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserRowMapper implements RowMapper<User> {
//
//    @Override
//    public User mapRow(ResultSet resultSet, int i) throws SQLException {
//        User user = new User();
//        user.setUserId(resultSet.getInt("user_id"));
//        user.setEmail(resultSet.getString("email"));
//        user.setPassword(resultSet.getString("PASSWORD"));
//        user.setCreatedDate(resultSet.getTimestamp("CREATED_DATE"));
//        user.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
//        return user;
//    }
//}
