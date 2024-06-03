package OOP;

public class MonHoc {
    private String IDMon;
    private String mon;

    public MonHoc(String IDMon, String mon) {
        this.IDMon = IDMon;
        this.mon = mon;
    }

    // Getters và setters
    public String getIDMon() {
        return IDMon;
    }

    public void setIDMon(String IDMon) {
        this.IDMon = IDMon;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }
}