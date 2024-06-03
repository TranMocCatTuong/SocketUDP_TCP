package OOP;

public class HocSinh {
	private String IDHocSinh;
    private String HoTenHocSinh;
    private String DiaChiHocSinh;
    private int NamNhapHoc;
    private String MatKhau;

    // Constructors
    public HocSinh(String IDHocSinh, String HoTenHocSinh, String DiaChiHocSinh, int NamNhapHoc, String MatKhau) {
        this.IDHocSinh = IDHocSinh;
        this.HoTenHocSinh = HoTenHocSinh;
        this.DiaChiHocSinh = DiaChiHocSinh;
        this.NamNhapHoc = NamNhapHoc;
        this.MatKhau = MatKhau;
    }

    

	// Getters and setters
    public String getIDHocSinh() {
        return IDHocSinh;
    }

    public void setIDHocSinh(String IDHocSinh) {
        this.IDHocSinh = IDHocSinh;
    }

    public String getHoTenHocSinh() {
        return HoTenHocSinh;
    }

    public void setHoTenHocSinh(String HoTenHocSinh) {
        this.HoTenHocSinh = HoTenHocSinh;
    }

    public String getDiaChiHocSinh() {
        return DiaChiHocSinh;
    }

    public void setDiaChiHocSinh(String DiaChiHocSinh) {
        this.DiaChiHocSinh = DiaChiHocSinh;
    }

    public int getNamNhapHoc() {
        return NamNhapHoc;
    }

    public void setNamNhapHoc(int NamNhapHoc) {
        this.NamNhapHoc = NamNhapHoc;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    
}
