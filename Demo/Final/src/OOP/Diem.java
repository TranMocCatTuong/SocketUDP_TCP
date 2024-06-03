package OOP;

public class Diem {
    private int hocKy;
    private String idMon;
    private String idHocSinh;
    private double diem;

    public Diem() {
    }

    public Diem(int hocKy, String idMon, String idHocSinh, double diem) {
        this.hocKy = hocKy;
        this.idMon = idMon;
        this.idHocSinh = idHocSinh;
        this.diem = diem;
    }

    // Getters and setters
    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

    public String getIdMon() {
        return idMon;
    }

    public void setIdMon(String idMon) {
        this.idMon = idMon;
    }

    public String getIdHocSinh() {
        return idHocSinh;
    }

    public void setIdHocSinh(String idHocSinh) {
        this.idHocSinh = idHocSinh;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}
