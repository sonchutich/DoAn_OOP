package tesst;

import dba.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Main.fxml"));
                rootPane.getChildren().setAll(pane);

                Stage modal_stage = new Stage();
                modal_stage.setScene(new Scene(pane));
                modal_stage.setTitle("modal");
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
