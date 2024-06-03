package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import OOP.Diem;

public class DiemDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/qlsv";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static Diem getByID(int hocKy, String idMon, String idHocSinh) {
        String sql = "SELECT * FROM Diem WHERE HocKy = ? AND IDMon = ? AND IDHocSinh = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hocKy);
            stmt.setString(2, idMon);
            stmt.setString(3, idHocSinh);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Diem(
                            rs.getInt("HocKy"),
                            rs.getString("IDMon"),
                            rs.getString("IDHocSinh"),
                            rs.getDouble("Diem")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static String getAllDiem() {
        String sql = "SELECT d.HocKy, d.IDMon, m.Mon, d.IDHocSinh, d.Diem, h.HoTenHocSinh " +
                     "FROM Diem d " +
                     "JOIN MonHoc m ON d.IDMon = m.IDMon " +
                     "JOIN HocSinh h ON d.IDHocSinh = h.IDHocSinh";
        String result = "";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                result += rs.getInt("HocKy") + "," + rs.getString("IDMon") + "," +rs.getString("Mon") + "," +rs.getString("IDHocSinh") + "," + rs.getString("HoTenHocSinh") + "," + rs.getDouble("Diem") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String BangDiemAll() {
        String result = "";
        String getAllDiem1 = getAllDiem();
        String[] lines = getAllDiem1.split("\n");
        String[][] temp = new String[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            temp[i] = lines[i].split(",");
        }
        Map<String, Map<String, Double>> hocSinhDiemMap = new HashMap<>();
        for (String[] item : temp) {
            String hocSinh = item[4];
            String monHoc = item[2];
            Double diem = Double.parseDouble(item[5]);

            if (!hocSinhDiemMap.containsKey(hocSinh)) {
                hocSinhDiemMap.put(hocSinh, new HashMap<String, Double>());
            }

            hocSinhDiemMap.get(hocSinh).put(monHoc, diem);
        }
        for (Map.Entry<String, Map<String, Double>> entry : hocSinhDiemMap.entrySet()) {
            String hocSinh = entry.getKey();
            Map<String, Double> diemMap = entry.getValue();

            result += "================================\n";
            result += "Họ và tên: " + hocSinh + "\n";
            for (Map.Entry<String, Double> diemEntry : diemMap.entrySet()) {
                String monHoc = diemEntry.getKey();
                Double diem = diemEntry.getValue();
                result += monHoc + ": " + diem + "\n";
            }
            result += "\n";
        }
        return result;
    }
    
    public static String TimDiemTheoIDHocSinh(String IDHocSinh) {
        String getAllDiem1 = getAllDiem();
        String[] lines = getAllDiem1.split("\n");
        String[][] temp = new String[lines.length][];
        boolean found = false;
        String result="";
        
        for (int i = 0; i < lines.length; i++) {
            temp[i] = lines[i].split(",");
        }
        
        for (int i = 0; i < temp.length; i++) {
            if (temp[i][3].equals(IDHocSinh)) {
                if (!found) {
                	result += "================================\n";
                    result += "Họ và tên: " + temp[i][4]+"\n";
                    found = true;
                }
                result += temp[i][2] + ": " + temp[i][5]+"\n";
            }
        }
        return result;
    }
    
    public static boolean updateDiem(String hocSinhID, String monHocID, double diem) {
        String sql = "UPDATE Diem SET Diem = ? WHERE IDHocSinh = ? AND IDMon = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, diem);
            stmt.setString(2, hocSinhID);
            stmt.setString(3, monHocID);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
            	return true;
            } else {
            	return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Các phương thức CRUD khác có thể được thêm vào ở đây
}