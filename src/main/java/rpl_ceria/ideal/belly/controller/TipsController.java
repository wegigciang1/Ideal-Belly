/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import rpl_ceria.ideal.belly.db.DaftarMakananDAO;

import rpl_ceria.ideal.belly.model.DaftarMakanan;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class TipsController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param private 
     */
     @FXML
     private Label selamatDatang_label;
    @FXML
    private AnchorPane anchor_lvl1;
    @FXML
    private AnchorPane anhcor_lvl2;
    @FXML
    private AnchorPane anchor_lvl3;
    @FXML
    private AnchorPane bilah_atas;
    @FXML
    private AnchorPane bilah_kiri;
    @FXML
    private AnchorPane bilah_kanan;
    @FXML
    private TableView<DaftarMakanan> Tabel_Makanan;
    @FXML
    private TableColumn<DaftarMakanan, Integer> Col_Id;
    @FXML
    private TableColumn<DaftarMakanan, String> Col_Nama;
    @FXML
    private TableColumn<DaftarMakanan, Double> Col_Berat;
    @FXML
    private TableColumn<DaftarMakanan, Double> Col_Kalori;
    //@FXML
   // private ListView<DaftarMakanan> list_Makanan;
    
    
    @FXML
    private void handleLogOutLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Log Out");
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/LoginStyles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void handleEditProfileLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Edit Profile");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EditProfile.fxml"));
        Parent root= (Parent) loader.load();
        
        Scene scene = new Scene(root);
//        //untuk melempar user
//        EditProfileController epc=loader.getController();
//        epc.tambah(userTamp);  
        //menampilkan window
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void handleHomeLinkAction(ActionEvent event) throws IOException {
        
        System.out.println("Request Home");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root= (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/HomeStyles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        HomeController home=loader.getController();
//        home.tambah(userTamp); 
        window.setScene(scene);
        window.show();
    }
    
     @FXML
    private void handleAktifitasTipsLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Tips");
        //Bagian Tips HyperLink
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AktifitasTips.fxml"));
        Parent root= (Parent) loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //untuk melempar user
//        TipsController tc=loader.getController();
//        tc.tambah(userTamp);  
        //menampilkan window
        window.setScene(scene);
        window.show();
        }
        catch(IOException e){
            System.out.println("Error Terjadi: " + e);
            throw e;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Tabel Makanan
        Col_Id.setCellValueFactory(new PropertyValueFactory("id"));
        Col_Nama.setCellValueFactory(new PropertyValueFactory("nama_makanan"));
        Col_Berat.setCellValueFactory(new PropertyValueFactory("berat"));
        Col_Kalori.setCellValueFactory(new PropertyValueFactory("kalori"));

        ObservableList<DaftarMakanan> data;
        try {
            data = DaftarMakananDAO.searchMakanans();
            Tabel_Makanan.setItems(data);
            //list_Makanan.setItems(data);
            
            System.out.println(data);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TipsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //resize TableColumn DaftarMakanan width
        Col_Id.prefWidthProperty().bind(Tabel_Makanan.widthProperty().divide(4));
        Col_Nama.prefWidthProperty().bind(Tabel_Makanan.widthProperty().divide(4));
        Col_Berat.prefWidthProperty().bind(Tabel_Makanan.widthProperty().divide(4));
        Col_Kalori.prefWidthProperty().bind(Tabel_Makanan.widthProperty().divide(4));
      
       
    
    }    

   
}
