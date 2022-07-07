package models;

import java.sql.Date;

public class Bill {
    int STT;
    String MaHD;
    String Ten;
    Date NgayHD;
    String MaKH;
    String MaNV;
    String MaMH;
    int SoLuong;
    int GiaBan;
    int Tongtien;
    int Thanhtien;

    public Bill() {
    }

    public Bill(int STT, String MaHD, Date NgayHD, int Tongtien) {
        this.STT = STT;
        this.MaHD = MaHD;
        this.NgayHD = NgayHD;
        this.Tongtien = Tongtien;
    }

    public Bill(int STT, String MaHD) {
        this.STT = STT;
        this.MaHD = MaHD;
    }

    public Bill(int STT, String MaMH, String TenMH, int SoLuong, int GiaBan, int Thanhtien) {
        this.STT = STT;
        this.MaMH = MaMH;
        this.Ten = TenMH;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.Thanhtien = Thanhtien;
    }

    public Bill(int STT, String MaHD, Date NgayLap, int TongTien, String MaNV) {
        this.STT = STT;
        this.MaHD = MaHD;
        this.NgayHD = NgayLap;
        this.Tongtien = TongTien;
        this.MaNV = MaNV;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getTongtien() {
        return Tongtien;
    }

    public void setTongtien(int Tongtien) {
        this.Tongtien = Tongtien;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public Date getNgayHD() {
        return NgayHD;
    }

    public void setNgayHD(Date NgayHD) {
        this.NgayHD = NgayHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String MaMH) {
        this.MaMH = MaMH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getThanhtien() {
        return Thanhtien;
    }

    public void setThanhtien(int Thanhtien) {
        this.Thanhtien = Thanhtien;
    }
    
    
}
