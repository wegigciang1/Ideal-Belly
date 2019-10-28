/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import rpl_ceria.ideal.belly.db.UserDAO;
import rpl_ceria.ideal.belly.model.User;

/**
 * FXML Controller class
 *
 * @author dewi
 */
public class ForgotPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private TextField email;
    
    @FXML
    private PasswordField password_field1;
    
    @FXML
    private PasswordField password_field2;
    
    private static String emailNow;
    
    public static String getEmailNow() {
        return emailNow;
    }

    public static void setEmailNow(String emailNow) {
        ForgotPasswordController.emailNow = emailNow;
    }
    
    @FXML
    private void handleResetPasswordButtonAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, ParseException {
        String emailUser = email.getText();
        
        User user = UserDAO.searchUserByEmail(emailUser);
        
        if(user != null) {
//            this.emailNow = email.getText();
            ForgotPasswordController.setEmailNow(email.getText());
            System.out.println("Email valid");
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PasswordRecovery.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/PasswordRecoveryStyles.css");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(scene);
            window.show();
            
        } else {
              JOptionPane.showMessageDialog(null, "Email tidak terdaftar");
        }
        
    }
    
    @FXML
    private void handleChangePasswordButtonAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, ParseException {
        String newPassword = password_field1.getText();
        String confirmPassword = password_field2.getText();
        
        User userNow = UserDAO.searchUserByEmail(ForgotPasswordController.getEmailNow());
        
        if(newPassword.equals(confirmPassword)){
            userNow.setPassword(newPassword);
            UserDAO.updateUser(userNow.getId(), userNow);
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/LoginStyles.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }
        else {
            JOptionPane.showMessageDialog(null, "Password tidak sama");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}