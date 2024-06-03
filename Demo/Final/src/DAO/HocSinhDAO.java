package DAO;
import OOP.HocSinh;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class HocSinhDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/qlsv";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static HocSinh getByID(String ID) {
        String sql = "SELECT * FROM HocSinh WHERE IDHocSinh = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new HocSinh(
                            rs.getString("IDHocSinh"),
                            rs.getString("HoTenHocSinh"),
                            rs.getString("DiaChiHocSinh"),
                            rs.getInt("NamNhapHoc"),
                            rs.getString("MatKhau")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String printAll() {
        String sql = "SELECT * FROM HocSinh";
        String result = "";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            result += "Danh sách các học sinh:\n";
            while (rs.next()) {
                result += "\nID Học Sinh: " + rs.getString("IDHocSinh");
                result += "\nHọ và Tên: " + rs.getString("HoTenHocSinh");
                result += "\nĐịa chỉ: " + rs.getString("DiaChiHocSinh");
                result += "\nNăm nhập học: " + rs.getInt("NamNhapHoc");
                result += "\n--------------------";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
    }
    public static String TimHocSinhTheoIDHocSinh(String ID) {
        String result = "";
        HocSinh hocSinh = getByID(ID);
        if (hocSinh != null) {
            result += "ID Học Sinh: " + hocSinh.getIDHocSinh() + "\n";
            result += "Họ và Tên: " + hocSinh.getHoTenHocSinh() + "\n";
            result += "Địa Chỉ: " + hocSinh.getDiaChiHocSinh() + "\n";
            result += "Năm Nhập Học: " + hocSinh.getNamNhapHoc() + "\n";
        } else {
            result = "Không tìm thấy học sinh có ID " + ID;
        }
        return result;
    }
    public static boolean themHocSinh(String IDHocSinh, String HoTen, String DiaChi, String NamHoc, String MatKhau) {
        int namHocInt = Integer.parseInt(NamHoc);

        String sqlThemHocSinh = "INSERT INTO HocSinh (IDHocSinh, HoTenHocSinh, DiaChiHocSinh, NamNhapHoc, MatKhau) VALUES (?, ?, ?, ?, ?)";
        String sqlThemDiemMacDinh = "INSERT INTO Diem (HocKy, IDMon, IDHocSinh, Diem) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmtThemHocSinh = conn.prepareStatement(sqlThemHocSinh);
             PreparedStatement stmtThemDiem = conn.prepareStatement(sqlThemDiemMacDinh)) {

            stmtThemHocSinh.setString(1, IDHocSinh);
            stmtThemHocSinh.setString(2, HoTen);
            stmtThemHocSinh.setString(3, DiaChi);
            stmtThemHocSinh.setInt(4, namHocInt);
            stmtThemHocSinh.setString(5, MatKhau);
            int rowsAffectedHocSinh = stmtThemHocSinh.executeUpdate();
            
            int hocKyMacDinh = 1; 
            String[] monHocMacDinh = {"MH001", "MH002", "MH003"};
            
            for (String monHoc : monHocMacDinh) {
                stmtThemDiem.setInt(1, hocKyMacDinh);
                stmtThemDiem.setString(2, monHoc);
                stmtThemDiem.setString(3, IDHocSinh);
                stmtThemDiem.setDouble(4, 0);
                stmtThemDiem.addBatch();
            }
            int[] rowsAffectedDiem = stmtThemDiem.executeBatch();
            
            boolean hocSinhThemThanhCong = rowsAffectedHocSinh > 0;
            boolean diemThemThanhCong = Arrays.stream(rowsAffectedDiem).allMatch(count -> count == 1);
            
            return hocSinhThemThanhCong && diemThemThanhCong;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean xoaHocSinh(String IDHocSinh) {
        String sqlDiem = "DELETE FROM Diem WHERE IDHocSinh = ?";
        String sqlHocSinh = "DELETE FROM HocSinh WHERE IDHocSinh = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmtDiem = conn.prepareStatement(sqlDiem);
             PreparedStatement stmtHocSinh = conn.prepareStatement(sqlHocSinh)) {
            conn.setAutoCommit(false);

            stmtDiem.setString(1, IDHocSinh);
            int rowsAffectedDiem = stmtDiem.executeUpdate();

            stmtHocSinh.setString(1, IDHocSinh);
            int rowsAffectedHocSinh = stmtHocSinh.executeUpdate();

            if (rowsAffectedDiem > 0 && rowsAffectedHocSinh > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback(); 
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editHoTenHocSinh(String IDHocSinh, String HoTenMoi) {
        String sql = "UPDATE HocSinh SET HoTenHocSinh = ? WHERE IDHocSinh = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, HoTenMoi);
            stmt.setString(2, IDHocSinh);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean editDiaChiHocSinh(String IDHocSinh, String DiaChiMoi) {
        String sql = "UPDATE HocSinh SET DiaChiHocSinh = ? WHERE IDHocSinh = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, DiaChiMoi);
            stmt.setString(2, IDHocSinh);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean editNamHocHocSinh(String IDHocSinh, int NamHocMoi) {
        String sql = "UPDATE HocSinh SET NamNhapHoc = ? WHERE IDHocSinh = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, NamHocMoi);
            stmt.setString(2, IDHocSinh);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean checkLogin(String IDHocSinh, String password) {
        String sql = "SELECT * FROM HocSinh WHERE IDHocSinh = ? AND MatKhau = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, IDHocSinh);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean changePass(String IDHocSinh, String matkhaucu, String matkhaumoi) {
        if (!checkLogin(IDHocSinh, matkhaucu)) {
            System.out.println("Mật khẩu cũ không chính xác.");
            return false;
        }

        String sql = "UPDATE HocSinh SET MatKhau = ? WHERE IDHocSinh = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matkhaumoi);
            stmt.setString(2, IDHocSinh);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
