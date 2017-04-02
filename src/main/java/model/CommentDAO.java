package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Егор on 02.04.2017.
 */
public class CommentDAO {

    public static void main(String[] args) throws Exception {
        CommentDAO commentDAO = new CommentDAO("jdbc:mysql://localhost:3306/myblog", "root", "root");
        String strDate = "2011-12-31 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        java.util.Date date = sdf.parse(strDate);
        commentDAO.insertComment(new Comment("test", new Date(date.getTime()), 1, 2));
    }

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public CommentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO comment (text, date, iduser, idtheme) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, comment.getText());
        statement.setDate(2, comment.getDate());
        statement.setInt(3, comment.getIdUser());
        statement.setInt(4, comment.getIdTheme());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Comment> listAllComments() throws SQLException {
        List<Comment> listComments = new ArrayList<Comment>();

        String sql = "SELECT * FROM comment";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String text = resultSet.getString("text");
            Date date = resultSet.getDate("date");
            int id_user = resultSet.getInt("iduser");
            int id_theme = resultSet.getInt("idtheme");

            Comment comment = new Comment(id, text, date, id_user, id_theme);
            listComments.add(comment);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listComments;
    }

    public boolean deleteComment(Comment comment) throws SQLException {
        String sql = "DELETE FROM comment where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, comment.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateComment(Comment comment) throws SQLException {
        String sql = "UPDATE comment SET text = ?, date = ?, id_user = ?, id_theme = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, comment.getText());
        statement.setDate(2, comment.getDate());
        statement.setInt(3, comment.getIdUser());
        statement.setInt(4, comment.getIdUser());
        statement.setInt(5, comment.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Comment getComment(int id) throws SQLException {
        Comment comment = null;
        String sql = "SELECT * FROM comment WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String text = resultSet.getString("text");
            Date date = resultSet.getDate("date");
            int id_user = resultSet.getInt("iduser");
            int id_theme = resultSet.getInt("idtheme");

            comment = new Comment(id, text, date, id_user, id_theme);
        }

        resultSet.close();
        statement.close();

        return comment;
    }
}
