package oop.bufihofa.classes;

public class Storage {
    private String maKho;
    private String diaChiKho;

    public Storage(String maKho, String diaChiKho) {
        this.maKho = maKho;
        this.diaChiKho = diaChiKho;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getDiaChiKho() {
        return diaChiKho;
    }

    public void setDiaChiKho(String diaChiKho) {
        this.diaChiKho = diaChiKho;
    }
}
