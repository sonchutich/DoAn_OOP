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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Bill;
import models.Client;
import models.Product;

/**
 * FXML Controller class
 *
 * @author demon
 */
public class KhachHangController implements Initializable {

    @FXML
    private TextField txt_MaKH;
    
    @FXML
    private TextField txt_MaKH1;
    @FXML
    private TextField txt_TongTien;
    @FXML
    private Button btn_Them;
    @FXML
    private Button btn_Sua;
    @FXML
    private Button btn_Xoa;
    @FXML
    private TextField txt_TenKH;
    @FXML
    private TextField txt_SDTKH;
    @FXML
    private TableView<Client> tb_KhachHang;
    @FXML
    private TableColumn<Client, Integer> STT_col;
    @FXML
    private TableColumn<Client, String> MaKH_col;
    @FXML
    private TableColumn<Client, String> TenKH_col;
    @FXML
    private TableColumn<Client, String> SDTKH_col;
    @FXML
    private TextField txt_MaHD;
    @FXML
    private TableView<Bill> tb_DetailKH;
    @FXML
    private TableColumn<Bill, Integer> Col_STT;
    @FXML
    private TableColumn<Bill, String> Col_MaHD;
    @FXML
    private TableColumn<Bill, String> Col_NgayHD;
    @FXML
    private TableColumn<Bill, String> Col_TongTien;
    @FXML
    private TableColumn<Bill, String> Col_MaNV;
    @FXML
    private TableColumn<Bill, String> Col_Thanhtien1;

    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Product product = null;
    String st = null;
    
    ObservableList<Client> ClientList = FXCollections.observableArrayList();
    ObservableList<Bill> BillList = FXCollections.observableArrayList();
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
        loadKH();
    }

    private void refreshTable() {
        ClientList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY MaKH) AS [STT],MaKH,TenKH,SDTKH from KhachHang;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClientList.add(new Client(
                        rs.getInt("STT"),
                        rs.getString("MaKH"),
                        rs.getString("TenKH"),
                        rs.getString("SDTKH")));
                tb_KhachHang.setItems(ClientList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadKH() {

        refreshTable();
        STT_col.setCellValueFactory(new PropertyValueFactory<>("STT"));
        MaKH_col.setCellValueFactory(new PropertyValueFactory<>("MaKH"));
        TenKH_col.setCellValueFactory(new PropertyValueFactory<>("TenKH"));
        SDTKH_col.setCellValueFactory(new PropertyValueFactory<>("SDTKH"));

        tb_KhachHang.setItems(ClientList);
    }

    @FXML
    private void setDetail(MouseEvent event) {
        int MaKH;
        Client clickedProduct = tb_KhachHang.getSelectionModel().getSelectedItem();
        MaKH = Integer.valueOf(clickedProduct.getMaKH());
        loadDetail(MaKH);
        
        txt_MaKH.setText(clickedProduct.getMaKH());
        txt_MaKH1.setText(clickedProduct.getMaKH());
        txt_TenKH.setText(clickedProduct.getTenKH());
        txt_SDTKH.setText(clickedProduct.getSDTKH());
        thanhTien();
    }

    public void refreshTbDetail(int a) {
        BillList.clear();
        try {
            query = "select ROW_NUMBER() OVER (ORDER BY MaHD) AS [STT], b.MaHD, b.NgayHD, b.TongTien, b.MaNV from KhachHang a, HoaDon b where a.MaKH = b.MaKH and a.MaKH = "+a+";";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                BillList.add(new Bill(
                        rs.getInt("STT"),
                        rs.getString("MaHD"),
                        rs.getDate("NgayHD"),
                        rs.getInt("TongTien"),
                        rs.getString("MaNV")));
                tb_DetailKH.setItems(BillList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDetail(int MaKH) {
        refreshTbDetail(MaKH);
        
        Col_STT.setCellValueFactory(new PropertyValueFactory<>("STT"));
        Col_MaHD.setCellValueFactory(new PropertyValueFactory<>("MaHD"));
        Col_NgayHD.setCellValueFactory(new PropertyValueFactory<>("NgayHD"));
        Col_TongTien.setCellValueFactory(new PropertyValueFactory<>("Tongtien"));
        Col_MaNV.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        
        tb_DetailKH.setItems(BillList);
    }
    @FXML
    public void thanhTien() {
        TableColumn tablecolumn = tb_DetailKH.getColumns().get(3);
        int sum = 0;
        for (int i = 0; i < tb_DetailKH.getItems().size(); i++) {
            sum = sum + Integer.parseInt(String.valueOf(tablecolumn.getCellObservableValue(i).getValue()));
        }

        txt_TongTien.setText(String.valueOf(sum));

    }
    public boolean CheckKH() {
        int count = 0;
        try {
            query = "select COUNT(*) AS 'count' from KhachHang where SDTKH =" + txt_SDTKH.getText() + ";";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String columnLabel = "";
                count = rs.getInt(columnLabel = "count");
            }
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public void insertKH(ActionEvent e) {
        String TenKH = txt_TenKH.getText();
        String SDTKH = txt_SDTKH.getText();
        if (checkTextField(TenKH, SDTKH)) {
            try {
                query = "insert into KhachHang values (N'" + TenKH + "','" + SDTKH + "');";
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println("Done");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadKH();
            txt_MaKH.clear();
            txt_TenKH.clear();
            txt_SDTKH.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("INSERT ERROR");
            alert.setContentText("Error");
            String s = "";

            ButtonType buttonOK = new ButtonType(s = "OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(buttonOK);
            alert.show();
        }
    }

    public boolean checkTextField(String TenKH, String SDTKH) {
        if (TenKH.isEmpty()) {
            st = "Nhập tên khách hàng";
            return false;
        } else if (SDTKH.isEmpty()) {
            st = "Nhập SĐT khách hàng";
            return false;
        } else {
            return true;
        }
    }
    public void deleteKH(){
        String MaKH = txt_MaKH.getText();
            try {
                query = "exec DeleteKH "+MaKH+";";
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println("Done");
                    txt_MaKH.clear();
                    txt_TenKH.clear();
                    txt_SDTKH.clear();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadKH();
    }
    
}
