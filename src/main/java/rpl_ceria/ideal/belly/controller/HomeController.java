/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rpl_ceria.ideal.belly.model.BeratHarian;
import rpl_ceria.ideal.belly.db.BeratHarianDAO;
import rpl_ceria.ideal.belly.model.UserSession;
import rpl_ceria.ideal.belly.model.User;

/**
 * FXML Controller class
 *
 * @author nathania
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    private Button button_add;
    
    private Alert formBodyWeightRoot;
    
    @FXML
    private void handleLogOutLinkAction(ActionEvent event) throws IOException {
        System.out.println("Request Log Out");
        
        UserSession.setUserSession(null);
        
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
        
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/EditProfile.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void handleAddBodyWeightButtonAction(boolean isAdd, final BeratHarian brt) {
        System.out.println("Request Add Body Weight");
        
        if(formBodyWeightRoot == null){
            formBodyWeightRoot = new Alert(INFORMATION);
            formBodyWeightRoot.setTitle("Berat Badan Harian");
            formBodyWeightRoot.setHeaderText("Tambah Berat Badan Harian");
            formBodyWeightRoot.initModality(Modality.WINDOW_MODAL);
            ButtonType button_simpan = new ButtonType("Simpan");
            ButtonType button_batal = new ButtonType("Batal", ButtonBar.ButtonData.CANCEL_CLOSE);
            formBodyWeightRoot.getButtonTypes().setAll(button_simpan, button_batal);
            
            FormBodyWeightController brtController = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormBodyWeight.fxml"));
            try {
                Node content = loader.load();
                DialogPane pane = formBodyWeightRoot.getDialogPane();
                pane.setContent(content);
                brtController = loader.getController();
                brtController.setBeratHarian(brt);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Optional<ButtonType> result = formBodyWeightRoot.showAndWait();

            if (result.get() == button_simpan) {
                if (brtController != null){
                    if (isAdd) {
                        // tambahkan data ke tabel
                        try {
                            BeratHarian brtUpdated = brtController.getBeratHarian();
                            if(!Pattern.matches("[0-9]+[\\.0-9]+", String.valueOf(brtUpdated.getBerat_badan()))){
                                throw new Exception("Invalid Input");
                            }
                            BeratHarianDAO.addBeratHarian(brtUpdated);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception e){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid Input");
                            alert.setHeaderText("Pengisian Tidak Benar");
                            alert.setContentText("Berat badan harus angka");
                            alert.showAndWait();
                        }
                    } else {
                        String emailOld = brt.getEmail();
                        BeratHarian brtUpdated = brtController.getBeratHarian();
                        try {
                            BeratHarianDAO.updateBeratHarian(emailOld, brtUpdated);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                formBodyWeightRoot = null;
            } else if (result.get() == button_batal) {
                formBodyWeightRoot = null;
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        button_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User userNow = UserSession.getUserSession();
                try {
                    BeratHarian cek_beratharian = BeratHarianDAO.searchBeratHarianByEmailAndDate(userNow.getEmail(), LocalDate.now(ZoneId.systemDefault()));
                    if(cek_beratharian == null){    
                        handleAddBodyWeightButtonAction(true, new BeratHarian(userNow.getEmail(), LocalDate.now(ZoneId.systemDefault()), 0));
                    }
                    else{
                        handleAddBodyWeightButtonAction(false, new BeratHarian(cek_beratharian.getEmail(), cek_beratharian.getTanggal_harian(), cek_beratharian.getBerat_badan()));
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }       
}