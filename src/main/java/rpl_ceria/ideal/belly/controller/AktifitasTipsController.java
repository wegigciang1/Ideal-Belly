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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import rpl_ceria.ideal.belly.db.DaftarAktifitasDAO;
import rpl_ceria.ideal.belly.model.DaftarAktifitas;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class AktifitasTipsController implements Initializable {

    @FXML
    private AnchorPane bilah_kanan;
    
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
    private void handleTipsLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Tips");
        //Bagian Tips HyperLink
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tips.fxml"));
        Parent root= (Parent) loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/TipsStyles.css");
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
    
    @FXML
    private void handleEditProfileLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Edit Profile");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EditProfile.fxml"));
        Parent root= (Parent) loader.load();
        
        Scene scene = new Scene(root);
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
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          //Tabel Aktifitas
        
        try {
            Label title_tipsmakanan = new Label("Rekomendasi Aktifitas");
            title_tipsmakanan.setLayoutX(16.0);
            title_tipsmakanan.setLayoutY(16.0);
            title_tipsmakanan.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
            this.bilah_kanan.getChildren().addAll(title_tipsmakanan);
            
            ObservableList<DaftarAktifitas> data;
            data = DaftarAktifitasDAO.searchAktifitass();
            // GridPane untuk setiap data tips
            double layoutX = 16.0, layoutY = 50.0;
            int gridColumns = 0;
            
            for(DaftarAktifitas item : data){
                GridPane grid = new GridPane();
                grid.setVgap(10);
                grid.setPrefSize(190, 100);
                grid.setPadding(new Insets(10,10,10,10));
                grid.getStyleClass().add("tips-grid");
                grid.setStyle("-fx-background-image:url(\"file:db_pictures/aktifitas/" + item.getPath_img() + "\");");
                
                grid.setLayoutX(layoutX); 
                grid.setLayoutY(layoutY);
                gridColumns++;
                if(gridColumns == 2){
                    layoutX = 16.0;
                    layoutY += 105.0;
                    gridColumns = 0;
                } else{
                    layoutX += 195.0;
                }
                        
                // add detail data ke grid
                Label nama_makanan = new Label(item.getAktifitas());
                nama_makanan.setPrefWidth(190);
                nama_makanan.setWrapText(true);
                nama_makanan.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                grid.add(nama_makanan, 1, 0);
                
                Label jumlah_kalori = new Label("Kalori : " + String.valueOf(item.getKalori_terbakar()) + " / 30 menit");
                jumlah_kalori.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
                grid.add(jumlah_kalori, 1, 1);
                
                this.bilah_kanan.getChildren().addAll(grid);   
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TipsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
