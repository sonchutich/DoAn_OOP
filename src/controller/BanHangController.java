/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dba.ConnectDB;
import java.net.URL;
import java.lang.String;
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
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import models.Product;

/**
 * FXML Controller class
 *
 * @author demon
 */
public class BanHangController implements Initializable {

    @FXML
    private Button btnThem;
    @FXML
    private TextField txt_MaHH;
    @FXML
    private TextField txt_MaNV;
    @FXML
    private TextField txt_MaHD;
    @FXML
    private TextField txt_NgayHD;
    @FXML
    private TextField txt_SDTKH;
    @FXML
    private TextField txt_TenKH;
    @FXML
    private Button btn_checkKH;
    @FXML
    private TextField txt_MaKH;
    @FXML
    private Button btn_ThemKH;
    @FXML
    private TableView<Product> tbHangHoa;
    @FXML
    private TableColumn<Product, String> maCol;
    @FXML
    private TableColumn<Product, String> TenCol;
    @FXML
    private TableColumn<Product, Integer> SLCol;
    @FXML
    private TableColumn<Product, String> GiaCol;
    @FXML
    private TableColumn<Product, String> TienCol;
    @FXML
    private TableColumn<Product, String> GhiChuCol;
    @FXML
    private TableColumn<Product, String> editCol;
    @FXML
    private Button btnThanhToan;
    @FXML
    private Button btnHuy;
    @FXML
    private TextField txt_ThanhTien;
    @FXML
    private TextField txt_TraLai;
    @FXML
    private TextField txt_KhachTra;
    @FXML
    private Button btn_delete;
    @FXML
    private TextField txt_check;

    Integer total;
    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Product product = null;
    String st = "";
    ObservableList<Product> ProductList = FXCollections.observableArrayList();
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
        loadTable();
        autocal();
    }

    @FXML
    public void ThemHang(ActionEvent event) throws SQLException {
        try {
            query = "SELECT a.MaMH, b.TenMH, a.GiaBan FROM LoHang a, MatHang b where a.MaMH = b.MaMH and a.MaMH = '" + txt_MaHH.getText() + "' group by a.MaMH, b.TenMH, a.GiaBan;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("MaMH"),
                        rs.getString("TenMH"),
                        1,
                        rs.getInt("GiaBan"));
                product.setThanhTien(product.getGiaBan() * product.getSoLuong());
                ProductList.add(product);

                tbHangHoa.setItems(ProductList);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTable();
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

    public void DisplayKH(ActionEvent event) {
        if (CheckKH()) {
            try {
                query = "select MaKH,TenKH from KhachHang where SDTKH =" + txt_SDTKH.getText() + ";";
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    txt_MaKH.setText(rs.getString("MaKH"));
                    txt_TenKH.setText(rs.getString("TenKH"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            txt_check.setText("NULL");
        }
    }

    public void insertKH(ActionEvent e) {
        String TenKH = txt_TenKH.getText();
        String SDTKH = txt_SDTKH.getText();
        if (checkTextField(TenKH, SDTKH)) {
            try {
                query = "insert into KhachHang values ('" + TenKH + "','" + SDTKH + "');";
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println("Done");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            txt_check.setText("INSERT DONE");
            DisplayKH(e);
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

    public void displaymaHD() {
        try {
            query = "select MAX(MaHD) as MaHD from HoaDon;";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int newHD = Integer.valueOf(rs.getString("MaHD")) + 1;
                txt_MaHD.setText(String.valueOf(newHD));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTable() {
        txt_MaNV.setText(FXMLDocumentController.username);
        maCol.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        TenCol.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        SLCol.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));

        GiaCol.setCellValueFactory(new PropertyValueFactory<>("GiaBan"));
        TienCol.setCellValueFactory(new PropertyValueFactory<>("ThanhTien"));

        tbHangHoa.setEditable(true);
        SLCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        thanhTien();

        txt_NgayHD.setText(String.valueOf(java.time.LocalDate.now()));
        displaymaHD();
        txt_MaKH.setText("NULL");
    }

    @FXML
    public void thanhTien() {
        TableColumn tablecolumn = tbHangHoa.getColumns().get(4);
        int sum = 0;
        for (int i = 0; i < tbHangHoa.getItems().size(); i++) {
            sum = sum + Integer.parseInt(String.valueOf(tablecolumn.getCellObservableValue(i).getValue()));
        }

        txt_ThanhTien.setText(String.valueOf(sum));

    }

    public void setProduct(Product product) {
    }

    public void onEditChange(TableColumn.CellEditEvent<Product, Integer> productIntegerCellEidtEvent) {
        Product clickedProduct = tbHangHoa.getSelectionModel().getSelectedItem();
        setProduct(clickedProduct);
        productIntegerCellEidtEvent.getNewValue();
        clickedProduct.setSoLuong(productIntegerCellEidtEvent.getNewValue());
        clickedProduct.setThanhTien(clickedProduct.getGiaBan() * clickedProduct.getSoLuong());

        loadTable();
    }

    public void delete(ActionEvent event) {
        Product clickedProduct = tbHangHoa.getSelectionModel().getSelectedItem();
        tbHangHoa.getItems().remove(clickedProduct);
        loadTable();
    }

    public void autocal() {
        txt_KhachTra.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                int thanhtien = Integer.valueOf(txt_ThanhTien.getText());
                int tralai;
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    int khachtra = Integer.valueOf(txt_KhachTra.getText());
                    tralai = khachtra - thanhtien;
                    txt_TraLai.setText(String.valueOf(tralai));
                }
            }
        });
    }

    @FXML
    public void ThanhToan(ActionEvent event) {
        try {
            String Ngayhd = txt_NgayHD.getText();
            String MaKH = txt_MaKH.getText();
            String Tongtien = txt_ThanhTien.getText();
            query = "insert into HoaDon(NgayHD,MaKH,TongTien) values ('" + Ngayhd + "'," + MaKH + "," + Tongtien + ");";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Insert HD Done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        insertDetail();

    }

    public void insertDetail() {

        String MaHD = txt_MaHD.getText();
        String[] Array = new String[5];
        for (int i = 0; i < tbHangHoa.getItems().size(); i++) {
            Array[0] = String.valueOf(tbHangHoa.getColumns().get(0).getCellObservableValue(i).getValue());
            Array[2] = String.valueOf(tbHangHoa.getColumns().get(2).getCellObservableValue(i).getValue());
            Array[4] = String.valueOf(tbHangHoa.getColumns().get(4).getCellObservableValue(i).getValue());

            try {
                query = "insert into ChiTietHoaDon(MaHD, MaMH, SoLuongBan, Thanhtien) values (" + MaHD + ",'" + Array[0] + "'," + Array[2] + "," + Array[4] + ");";
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRM");
        alert.setContentText("THANH TOÁN THÀNH CÔNG");
        String s = "";

        ButtonType buttonOK = new ButtonType(s = "OK", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonOK);
        alert.show();
        loadTable();

    }

//    @FXML
//    private void Huy(ActionEvent event) {
//    }
}
