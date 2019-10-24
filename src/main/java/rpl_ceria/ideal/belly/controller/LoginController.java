package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {
    
    @FXML
    private TextField email;
    
    @FXML
    private PasswordField password;
    
//    @FXML
//    private Label error;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String name = email.getText();
        String pass = password.getText();
//       error.setText("");
        if(name.equals("ciang") && pass.equals("1234")) {
            System.out.println("Login Berhasil");
//            error.setText("");
            
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/LoginStyles.css");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(scene);
            window.show();
            
        } else {
//            error.setText(email atau password anda salah");
              JOptionPane.showMessageDialog(null, "Email atau Password Anda Salah!");
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
