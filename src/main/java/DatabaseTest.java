import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTest {
    private static final String URL = "jdbc:sqlite:C:\\Users\\klimu\\AppData\\Roaming\\DBeaverData\\workspace6\\.metadata\\sample-database-sqlite-1\\Chinook.db";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            // INSERT
            String insertSql = "INSERT INTO MediaType (Name) VALUES (?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                insertStmt.setString(1, "New Value");
                insertStmt.executeUpdate();
            }

            // SELECT
            String selectSql = "SELECT * FROM MediaType WHERE Name = ?";
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
                selectStmt.setString(1, "New Value");
                ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    System.out.println("MediaTypeId: " + resultSet.getInt("MediaTypeId"));
                    System.out.println("Name: " + resultSet.getString("Name"));
                }
            }

            // UPDATE
            String updateSql = "UPDATE MediaType SET Name = ? WHERE Name = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setString(1, "Updated Value");
                updateStmt.setString(2, "New Value");
                updateStmt.executeUpdate();
            }

            // DELETE
            String deleteSql = "DELETE FROM MediaType WHERE Name = ?";
            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
                deleteStmt.setString(1, "Updated Value");
                deleteStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
