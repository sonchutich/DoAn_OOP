/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dba.ConnectDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Bill;
import models.Client;
import models.Staff;

/**
 * FXML Controller class
 *
 * @author demon
 */
public class NhanVienController implements Initializable {

    @FXML
    private TextField txt_MaNV;
    @FXML
    private TextField txt_TenNV;
    @FXML
    private TextField txt_DiaChi;
    @FXML
    private TextField txt_SDT;
    @FXML
    private Button btn_Them;
    @FXML
    private Button btn_Sua;
    @FXML
    private Button btn_Xoa;
    @FXML
    private Button btn_Clean;
    @FXML
    private PasswordField pass_Old;
    @FXML
    private PasswordField pass_New;
    @FXML
    private PasswordField pass_NewConfirm;
    @FXML
    private Button btn_ChangePass;

    @FXML
    private TableView<Staff> tb_NhanVien;
    @FXML
    private TableColumn<Staff, Integer> STT_col;
    @FXML
    private TableColumn<Staff, String> MaNV_col;
    @FXML
    private TableColumn<Staff, String> TenNV_col;
    @FXML
    private TableColumn<Staff, String> DiaChi_col;
    @FXML
    private TableColumn<Staff, String> SDT_col;
    
    @FXML
    private TextField txt_Search_MaHD;
    @FXML
    private DatePicker txt_Search_NgayBD;
    @FXML
    private DatePicker txt_Search_NgayKT;
    @FXML
    private TextField txt_NgayKT;
    @FXML
    private Button btn_TimKiemMaHD;
    @FXML
    private Button btn_LocTheoNgay;

    @FXML
    private TableView<Bill> tb_HoaDon;
    @FXML
    private TableColumn<Bill, Integer> Col_STT;
    @FXML
    private TableColumn<Bill, String> Col_MaHD;
    @FXML
    private TableColumn<Bill, String> Col_NgayLap;
    @FXML
    private TableColumn<Bill, String> Col_MaKH;
    @FXML
    private TableColumn<Bill, Integer> Col_TongTien;

    @FXML
    private TableView<Bill> tb_DetailHD;
    @FXML
    private TableColumn<Bill, Integer> Col1_STT;
    @FXML
    private TableColumn<Bill, String> Col1_MaMH;
    @FXML
    private TableColumn<Bill, String> Col1_TenMH;
    @FXML
    private TableColumn<Bill, Integer> Col1_SL;
    @FXML
    private TableColumn<Bill, Integer> Col1_ThanhTien;

    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String st = null;

    ObservableList<Staff> StaffList = FXCollections.observableArrayList();
    ObservableList<Bill> BillList = FXCollections.observableArrayList();
    ObservableList<Bill> DetailBillList = FXCollections.observableArrayList();
    Connection con = ConnectDB.getConnect();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadNV();
    }

    private void refreshTable() {
        StaffList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY MaNV) AS [STT],MaNV, TenNV,DiaChiNV,SDTNV from NhanVien;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                StaffList.add(new Staff(
                        rs.getInt("STT"),
                        rs.getString("MaNV"),
                        rs.getString("TenNV"),
                        rs.getString("DiaChiNV"),
                        rs.getString("SDTNV")));
                tb_NhanVien.setItems(StaffList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadNV() {

        refreshTable();
        STT_col.setCellValueFactory(new PropertyValueFactory<>("STT"));
        MaNV_col.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        TenNV_col.setCellValueFactory(new PropertyValueFactory<>("TenNV"));
        DiaChi_col.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        SDT_col.setCellValueFactory(new PropertyValueFactory<>("SDT"));

        tb_NhanVien.setItems(StaffList);
    }

    @FXML
    private void setDetail1(MouseEvent event) {
        int MaNV;
        Staff clickedProduct = tb_NhanVien.getSelectionModel().getSelectedItem();
        MaNV = Integer.valueOf(clickedProduct.getMaNV());
        loadDetail(MaNV);

        txt_MaNV.setText(clickedProduct.getMaNV());
        txt_TenNV.setText(clickedProduct.getTenNV());
        txt_DiaChi.setText(clickedProduct.getDiaChi());
        txt_SDT.setText(clickedProduct.getSDT());

    }

    public void refreshTbDetail(int a) {
        BillList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY MaHD) AS [STT], b.MaHD, b.NgayHD, b.MaKH, b.TongTien from NhanVien a, HoaDon b where a.MaNV = b.MaNV and a.MaNV = " + a + ";";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                BillList.add(new Bill(
                        rs.getInt("STT"),
                        rs.getString("MaHD"),
                        rs.getDate("NgayHD"),
                        rs.getString("MaKH"),
                        rs.getInt("TongTien")));
                tb_HoaDon.setItems(BillList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDetail(int MaNV) {
        refreshTbDetail(MaNV);

        Col_STT.setCellValueFactory(new PropertyValueFactory<>("STT"));
        Col_MaHD.setCellValueFactory(new PropertyValueFactory<>("MaHD"));
        Col_NgayLap.setCellValueFactory(new PropertyValueFactory<>("NgayHD"));
        Col_MaKH.setCellValueFactory(new PropertyValueFactory<>("MaKH"));
        Col_TongTien.setCellValueFactory(new PropertyValueFactory<>("Tongtien"));

        tb_HoaDon.setItems(BillList);
    }

    @FXML
    private void setDetail2(MouseEvent event) {
        int MaHD;
        Bill clickedProduct = tb_HoaDon.getSelectionModel().getSelectedItem();
        MaHD = Integer.valueOf(clickedProduct.getMaHD());
        loadDetail2(MaHD);

    }

    public void refreshTbDetail2(int a) {
        DetailBillList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY a.MaHD) AS [STT], a.MaMH,b.TenMH,a.SoLuongBan ,a.Thanhtien from ChiTietHoaDon a, MatHang b where MaHD = " + a + " and a.MaMH = b.MaMH group by a.MaHD, a.MaMH, b.TenMH, a.SoLuongBan , a.Thanhtien;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetailBillList.add(new Bill(
                        rs.getInt("STT"),
                        rs.getString("MaMH"),
                        rs.getString("TenMH"),
                        rs.getInt("SoLuongBan"),
                        rs.getInt("ThanhTien")));
                tb_DetailHD.setItems(DetailBillList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDetail2(int MaHD) {
        refreshTbDetail2(MaHD);

        Col1_STT.setCellValueFactory(new PropertyValueFactory<>("STT"));
        Col1_MaMH.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        Col1_TenMH.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        Col1_SL.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
        Col1_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("Thanhtien"));

        tb_DetailHD.setItems(DetailBillList);
    }

    public void Clean(ActionEvent e) {
        txt_MaNV.clear();
        txt_TenNV.clear();
        txt_DiaChi.clear();
        txt_SDT.clear();
        
        pass_Old.clear();
        pass_New.clear();
        pass_NewConfirm.clear();
    }

    public void insertNV(ActionEvent e) {
        String MaNV = txt_MaNV.getText();
        String TenNV = txt_TenNV.getText();
        String DiaChi = txt_DiaChi.getText();
        String SDTNV = txt_SDT.getText();
        String MatKhau = String.valueOf(pass_Old.getText());
        try {
            query = "insert into NhanVien values ('" + MaNV + "',N'" + TenNV + "'," + MatKhau + ",N'" + DiaChi + "','" + SDTNV + "');";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadNV();
    }

    public void deleteNV(ActionEvent e) {
        String MaNV = txt_MaNV.getText();

        try {
            query = "delete from NhanVien where MaNV = " + MaNV + ";";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadNV();
        Clean(e);
    }

    public void updateNV(ActionEvent e) {
        String MaNV = txt_MaNV.getText();
        String TenNV = txt_TenNV.getText();
        String DiaChi = txt_DiaChi.getText();
        String SDTNV = txt_SDT.getText();
        try {
            query = "update NhanVien set TenNV = N'" + TenNV + "' , DiaChiNV = N'" + DiaChi + "', SDTNV = '" + SDTNV + "' where MaNV = '" + MaNV + "' ;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadNV();
    }

    public void changePass(ActionEvent e) {
        String passOld = null;
        String MatKhau = String.valueOf(pass_Old.getText());
        String MatKhauMoi = String.valueOf(pass_New.getText());
        String Confirm = String.valueOf(pass_NewConfirm.getText());
        try {
            query = "select MatKhau from NhanVien where MaNV = " + txt_MaNV.getText() + ";";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                passOld = rs.getString("MatKhau");
            }
            if (MatKhau.equals(passOld)) {
                if (MatKhauMoi.equals(Confirm)) {
                    try {
                        query = "update NhanVien set MatKhau = " + MatKhauMoi + " where MaNV = '" + txt_MaNV.getText() + "' ;";
                        ps = con.prepareStatement(query);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            System.out.println("Done");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("CONFIRM");
                    alert.setContentText("ĐÔI MẬT KHẨU THÀNH CÔNG");
                    String s = "";

                    ButtonType buttonOK = new ButtonType(s = "OK", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonOK);
                    alert.show();
                    Clean(e);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Confirm PASS ERROR");
                    String s = "";
                    ButtonType buttonOK = new ButtonType(s = "OK", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonOK);
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("Sai mật khẩu");
                String s = "";
                ButtonType buttonOK = new ButtonType(s = "OK", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonOK);
                alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void filterHD(ActionEvent e){
        LocalDate NgayBD = txt_Search_NgayBD.getValue();
        LocalDate NgayKT = txt_Search_NgayKT.getValue();
        String MaNV = txt_MaNV.getText();
        BillList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY MaHD) AS [STT], b.MaHD, b.NgayHD, b.MaKH, b.TongTien from NhanVien a, HoaDon b \n" +
                "where a.MaNV = b.MaNV and a.MaNV = '"+MaNV+"' and NgayHD between '"+NgayBD+"' and '"+NgayKT+"';";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                BillList.add(new Bill(
                        rs.getInt("STT"),
                        rs.getString("MaHD"),
                        rs.getDate("NgayHD"),
                        rs.getString("MaKH"),
                        rs.getInt("TongTien")));
                tb_HoaDon.setItems(BillList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void TimKiemHD(ActionEvent e) {
        String MaHD = txt_Search_MaHD.getText();
        String MaNV = txt_MaNV.getText();
        BillList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY MaHD) AS [STT], b.MaHD, b.NgayHD, b.MaKH, b.TongTien from NhanVien a, HoaDon b \n" +
                "where a.MaNV = b.MaNV and a.MaNV = '"+MaNV+"' and b.MaHD = "+MaHD+";";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                BillList.add(new Bill(
                        rs.getInt("STT"),
                        rs.getString("MaHD"),
                        rs.getDate("NgayHD"),
                        rs.getString("MaKH"),
                        rs.getInt("TongTien")));
                tb_HoaDon.setItems(BillList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void refresh(ActionEvent e){
        String MaNV = txt_MaNV.getText();
        txt_Search_MaHD.clear();
        txt_Search_NgayBD.getEditor().clear();
        txt_Search_NgayKT.getEditor().clear();
        loadDetail(Integer.valueOf(MaNV));
    }
}
