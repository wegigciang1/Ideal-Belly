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
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
            if(email.getText().isEmpty() || password.getText().isEmpty() || nama.getText().isEmpty() || tanggal_lahir.getValue() == null || tinggi_badan.getText().isEmpty()){
               
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Invalid Registration");
//                alert.setHeaderText("Wajib Mengisi Semua Data");
//                alert.setContentText("Data Tidak Boleh Kosong");
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Registration");
                alert.setHeaderText("Wajib Mengisi Semua Data");
                alert.setContentText("Data Tidak Boleh Kosong");
                alert.showAndWait();
                throw new IOException();
            }
            
            String popupmessage = "";
            boolean flag = false;
            if(!Pattern.matches("[a-zA-Z]+", nama.getText())){
                popupmessage = "Nama harus huruf";
                flag = true;
            }
            if(!email.getText().contains("@") || !email.getText().contains(".")){
                if(flag){
                    popupmessage = popupmessage + "\nEmail tidak valid";
                }
                else{
                    popupmessage = "Email tidak valid";
                }
                flag = true;
            }
            if(Pattern.matches("[a-zA-Z]+", password.getText()) || Pattern.matches("[0-9]+", password.getText()) 
                    || password.getText().length() < 8){
                if(flag){
                    popupmessage = popupmessage + "\nPassword harus angka & huruf min 8 karakter";
                }
                else{
                    popupmessage = "Password harus angka & huruf min 8 karakter";
                }
                flag = true;
            }
            if(!Pattern.matches("[0-9]+[\\.0-9]+", tinggi_badan.getText())){
                if(flag){
                    popupmessage = popupmessage + "\nTinggi badan harus angka";
                }
                else{
                    popupmessage = "Tinggi badan harus angka";
                }
                flag = true;
            }
            else{
                flag = false;
            }
            if(popupmessage != ""){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Pengisian Tidak Benar");
                alert.setContentText(popupmessage);
                alert.showAndWait();
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
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Success");
                alert.setHeaderText("Daftar Berhasil");
                alert.setContentText("Akun Anda Berhasil Dibuat");
                alert.showAndWait();
                System.out.println("Sign Up Done");
                
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/LoginStyles.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            } else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Registration");
                alert.setHeaderText("Pendaftaran Gagal");
                alert.setContentText("Email Sudah Terdaftar");
                alert.showAndWait();
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
