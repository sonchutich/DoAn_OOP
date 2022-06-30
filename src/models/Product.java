package models;

import java.sql.Date;
import java.sql.Time;

public class Product {
    int STT;
    int MaPN;
    Date NgayNhap;
    String MaMH;
    String Ten;
    String MaLo;
    int SoLuong;
    int GiaBan;
    int ThanhTien;
    String GhiChu;
    String edit;
    String DVT;
    int GiaNhap;
    Time GioNhap;
    Date HSD;
    String NCC;

    public Product() {
    }
    
    public Product(int MaPN,String MaLo, String MaMH, String TenMH, String DVT, int GiaNhap, int SoLuong, int GiaBan, Date NgayNhap, Time GioNhap, Date HSD, String NCC) {
        this.MaPN = MaPN;
        this.MaLo = MaLo;
        this.MaMH = MaMH;
        this.Ten = TenMH;
        this.DVT = DVT;
        this.GiaNhap = GiaNhap;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.NgayNhap = NgayNhap;
        this.GioNhap = GioNhap;
        this.HSD = HSD;
        this.NCC = NCC;
    }

    public Product(String MaMH, String TenMH, int GiaBan) {
        this.MaMH = MaMH;
        this.Ten = TenMH; 
        this.GiaBan = GiaBan;
    }

    public Product( String MaMH, String TenMH, int SoLuong, int GiaBan) {
        this.MaMH = MaMH;
        this.Ten = TenMH;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan; 
    }

    public Product(int STT, String MaMH, String TenMH, String DVT, int SoLuong, int GiaBan) {
        this.STT = STT;
        this.MaMH = MaMH;
        this.Ten = TenMH;
        this.DVT = DVT;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan; 
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getMaPN() {
        return MaPN;
    }

    public void setMaPN(int MaPN) {
        this.MaPN = MaPN;
    }

    public Time getGioNhap() {
        return GioNhap;
    }

    public void setGioNhap(Time GioNhap) {
        this.GioNhap = GioNhap;
    }

    public Date getHSD() {
        return HSD;
    }

    public void setHSD(Date HSD) {
        this.HSD = HSD;
    }

    public String getNCC() {
        return NCC;
    }

    public void setNCC(String NCC) {
        this.NCC = NCC;
    }
    
    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    
    public String getMaLo() {
        return MaLo;
    }

    public void setMaLo(String MaLo) {
        this.MaLo = MaLo;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public int getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(int GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String MaMH) {
        this.MaMH = MaMH;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }
    
}
