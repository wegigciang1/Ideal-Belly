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
public class User {

    /**
     * @return the status
     */
    public String getStatus() {
        return status.get();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    /**
     * @return the jenis_kelamin
     */
    public String getJenis_kelamin() {
        return jenis_kelamin.get();
    }

    /**
     * @param jenis_kelamin the jenis_kelamin to set
     */
    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin.set(jenis_kelamin);
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
     * @return the nama
     */
    public String getNama() {
        return nama.get();
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama.set(nama);
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
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * @return the tanggal_lahir
     */
    public LocalDate getTanggal_lahir() {
        return tanggal_lahir;
    }

    /**
     * @param tanggal_lahir the tanggal_lahir to set
     */
    public void setTanggal_lahir(LocalDate tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    /**
     * @return the tinggi_badan
     */
    public double getTinggi_badan() {
        return tinggi_badan.get();
    }

    /**
     * @param tinggi_badan the tinggi_badan to set
     */
    public void setTinggi_badan(double tinggi_badan) {
        this.tinggi_badan.set(tinggi_badan);
    }
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty nama = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private LocalDate tanggal_lahir;
    private SimpleDoubleProperty tinggi_badan = new SimpleDoubleProperty();
    private SimpleStringProperty status = new SimpleStringProperty();
    private SimpleStringProperty jenis_kelamin = new SimpleStringProperty();
}
