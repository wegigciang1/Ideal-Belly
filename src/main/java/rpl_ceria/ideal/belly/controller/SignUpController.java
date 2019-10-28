/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import rpl_ceria.ideal.belly.db.UserDAO;
import rpl_ceria.ideal.belly.model.User;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class SignUpController implements Initializable {
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private TextField email;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField nama;
    
    @FXML
    private DatePicker tanggal_lahir;
    
    @FXML
    private TextField tinggi_badan;
                  
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException, ParseException {
        
        try{
            if(email.getText().isEmpty() || password.getText().isEmpty() || nama.getText().isEmpty() || tinggi_badan.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Data harus diisi semua");
                throw new IOException();
            }
            
                
            User newuser = new User();
            newuser.setEmail(email.getText());
            newuser.setPassword(password.getText());
            newuser.setNama(nama.getText());
            newuser.setTanggal_lahir(tanggal_lahir.getValue());
            newuser.setTinggi_badan(Double.parseDouble(tinggi_badan.getText()));
            User user = UserDAO.searchUserByEmail(newuser.getEmail());
            if (user == null) {
                UserDAO.addUser(newuser);
                JOptionPane.showMessageDialog(null, "Akun berhasil ditambahkan");
                System.out.println("Sign Up Done");
                
                JOptionPane.showMessageDialog(null, "Password berhasil diganti");
                
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/LoginStyles.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Email sudah terdaftar");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
