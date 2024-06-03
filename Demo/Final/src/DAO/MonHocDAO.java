package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import OOP.MonHoc;

public class MonHocDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/qlsv";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void insert(MonHoc monHoc) {
        String sql = "INSERT INTO MonHoc (IDMon, Mon) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, monHoc.getIDMon());
            stmt.setString(2, monHoc.getMon());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void printAll() {
        String sql = "SELECT * FROM MonHoc";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Danh sách môn học:");
            while (rs.next()) {
                System.out.println("IDMon: " + rs.getString("IDMon"));
                System.out.println("Môn học: " + rs.getString("Mon"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Các phương thức CRUD khác có thể được thêm vào ở đây
}