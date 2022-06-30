package controller;

import dba.ConnectDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDocumentController {

    @FXML
    private Button btnDangNhap;

    @FXML
    private Button btnThoat;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;
    @FXML
    private AnchorPane rootPane;
    Connection con = ConnectDB.getConnect();
    String st="";
    
    @FXML
    void login(ActionEvent event) throws IOException, SQLException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        if(checkTextField(username, password)){
        if(checkAcc(username, password)){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/Dashboard.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root1));  
                stage.show();
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LOGIN ERROR");
            alert.setContentText("Sai Tài khoản hoặc Mật Khẩu");
            String s = "";
            
            ButtonType buttonOK = new ButtonType(s = "OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(buttonOK);
            alert.show();
        }
       }
    }
    
    public boolean checkAcc(String username, String password) throws SQLException{
        int count = 0;
        String st = "SELECT COUNT (*) AS 'count' FROM dbo.NhanVien WHERE MaNV='"+username+"' AND MatKhau='"+password+"';";
        try{
            ResultSet rs = con.createStatement().executeQuery(st);
            while(rs.next()){
                String columnLabel = "";
                count=rs.getInt(columnLabel= "count");
            }
            if(count == 1)
                return true;
            else
                return false;
             

        }catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }
    
    public boolean checkTextField(String username, String password){
        if(username.isEmpty()){
            st="Nhập tên đăng nhập.";
            return false;
        }else if(password.isEmpty()){
            st="Nhập mật khẩu.";
            return false;
        }else
            return true;
    }
    
}
