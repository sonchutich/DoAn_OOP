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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Bill;
import models.Product;

/**
 * FXML Controller class
 *
 * @author demon
 */
public class HoaDonController implements Initializable {

    @FXML
    private TextField txt_Search_MaHD;
    @FXML
    private DatePicker txt_Search_NgayBD;
    @FXML
    private DatePicker txt_Search_NgayKT;
    @FXML
    private TableView<Bill> tb_HoaDon;
    @FXML
    private TableColumn<Bill, Integer> STT_col;
    @FXML
    private TableColumn<Bill, String> MaHD_col;
    @FXML
    private TableColumn<Bill, String> Ngay_col;
    @FXML
    private TableColumn<Bill, String> TongTien_col;
    @FXML
    private TextField txt_MaHD;
    @FXML
    private TextField txt_NgayHD;
    @FXML
    private TextField txt_TongTien;
    @FXML
    private TableView<Bill> tb_DetailHD;
    @FXML
    private TableColumn<Bill, Integer> Col_STT;
    @FXML
    private TableColumn<Bill, String> Col_MaMH;
    @FXML
    private TableColumn<Bill, String> Col_Ten;
    @FXML
    private TableColumn<Bill, String> Col_SL;
    @FXML
    private TableColumn<Bill, String> Col_Gia;
    @FXML
    private TableColumn<Bill, String> Col_Thanhtien;

    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Product product = null;

    ObservableList<Bill> BillList = FXCollections.observableArrayList();
    ObservableList<Bill> DetailBillList = FXCollections.observableArrayList();
    Connection con = ConnectDB.getConnect();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
        loadHD();
    }

    private void refreshTable() {
        BillList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY MaHD) AS [STT],MaHD,NgayHD,TongTien from HoaDon;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                BillList.add(new Bill(
                        rs.getInt("STT"),
                        rs.getString("MaHD"),
                        rs.getDate("NgayHD"),
                        rs.getInt("TongTien")));
                tb_HoaDon.setItems(BillList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadHD() {
        
        refreshTable();
        STT_col.setCellValueFactory(new PropertyValueFactory<>("STT"));
        MaHD_col.setCellValueFactory(new PropertyValueFactory<>("MaHD"));
        Ngay_col.setCellValueFactory(new PropertyValueFactory<>("NgayHD"));
        TongTien_col.setCellValueFactory(new PropertyValueFactory<>("Tongtien"));
        
        tb_HoaDon.setItems(BillList);
    }
    
    public void setDetail(MouseEvent event){
        int mahd;
        DateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Bill clickedProduct = tb_HoaDon.getSelectionModel().getSelectedItem();
        String ngayhd = dateFormat.format(clickedProduct.getNgayHD());
        mahd = Integer.valueOf(clickedProduct.getMaHD());
        loadDetail(mahd);
        txt_MaHD.setText(clickedProduct.getMaHD());
        txt_NgayHD.setText(ngayhd);
        txt_TongTien.setText(String.valueOf(clickedProduct.getTongtien()));
    }
    
    public void refreshTbDetail(int a){
        DetailBillList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY a.MaHD) AS [STT], a.MaMH,b.TenMH,a.SoLuongBan,c.GiaBan,a.Thanhtien from ChiTietHoaDon a, MatHang b, LoHang c where MaHD = "+ a +" and a.MaMH = b.MaMH and b.MaMH = c.MaMH group by a.MaHD, a.MaMH, b.TenMH, a.SoLuongBan  ,c.GiaBan, a.Thanhtien;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetailBillList.add(new Bill(
                        rs.getInt("STT"),
                        rs.getString("MaMH"),
                        rs.getString("TenMH"),
                        rs.getInt("SoLuongBan"),
                        rs.getInt("GiaBan"),
                        rs.getInt("ThanhTien")));
                tb_DetailHD.setItems(DetailBillList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDetail(int a){
        refreshTbDetail(a);
        Col_STT.setCellValueFactory(new PropertyValueFactory<>("STT"));
        Col_MaMH.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        Col_Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        Col_SL.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
        Col_Gia.setCellValueFactory(new PropertyValueFactory<>("GiaBan"));
        Col_Thanhtien.setCellValueFactory(new PropertyValueFactory<>("Thanhtien"));
        
        tb_DetailHD.setItems(DetailBillList);
    }
}
