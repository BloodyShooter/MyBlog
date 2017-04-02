package model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Егор on 01.04.2017.
 */
public class ThemeDAO extends ModelDAO {

    public ThemeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        super(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean insertTheme(Theme theme) throws SQLException {
        String sql = "INSERT INTO theme (title, text, date) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, theme.getTitle());
        statement.setString(2, theme.getText());
        statement.setDate(3, theme.getDate());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Theme> listAllThemes() throws SQLException {
        List<Theme> listThemes = new ArrayList<Theme>();

        String sql = "SELECT * FROM theme";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String text = resultSet.getString("text");
            Date date = resultSet.getDate("date");

            Theme theme = new Theme(id, title, text, date);
            listThemes.add(theme);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listThemes;
    }

    public boolean deleteTheme(Theme theme) throws SQLException {
        String sql = "DELETE FROM theme where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, theme.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateTheme(Theme theme) throws SQLException {
        String sql = "UPDATE theme SET title = ?, text = ?, date = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, theme.getTitle());
        statement.setString(2, theme.getText());
        statement.setDate(2, theme.getDate());
        statement.setInt(4, theme.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Theme getTheme(int id) throws SQLException {
        Theme theme = null;
        String sql = "SELECT * FROM theme WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String text = resultSet.getString("text");
            Date date = resultSet.getDate("date");

            theme = new Theme(id, title, text, date);
        }

        resultSet.close();
        statement.close();

        return theme;
    }
}
