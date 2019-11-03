/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author dewi
 */
public class BeratHarian {
    public BeratHarian(){
    }
    
    public BeratHarian(String email, LocalDate tanggal_harian, double berat_badan) {
        this.email = new SimpleStringProperty(email);
        this.tanggal_harian = tanggal_harian;
        this.berat_badan = new SimpleDoubleProperty(berat_badan);
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * @return the tanggal_harian
     */
    public LocalDate getTanggal_harian() {
        return tanggal_harian;
    }

    /**
     * @param tanggal_harian the tanggal_harian to set
     */
    public void setTanggal_harian(LocalDate tanggal_harian) {
        this.tanggal_harian = tanggal_harian;
    }

    /**
     * @return the berat_badan
     */
    public double getBerat_badan() {
        return berat_badan.get();
    }

    /**
     * @param berat_badan the berat_badan to set
     */
    public void setBerat_badan(double berat_badan) {
        this.berat_badan.set(berat_badan);
    }
    
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private LocalDate tanggal_harian;
    private SimpleDoubleProperty berat_badan = new SimpleDoubleProperty();
}
