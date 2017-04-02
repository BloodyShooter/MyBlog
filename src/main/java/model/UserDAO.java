package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Егор on 01.04.2017.
 */
public class UserDAO extends ModelDAO {

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        super(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user (login, password) VALUES (?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUsers = new ArrayList<User>();

        String sql = "SELECT * FROM user";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");

            User user = new User(id, login, password);
            listUsers.add(user);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listUsers;
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM user where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET login = ?, password = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");

            user = new User(id, login, password);
        }

        resultSet.close();
        statement.close();

        return user;
    }
}
