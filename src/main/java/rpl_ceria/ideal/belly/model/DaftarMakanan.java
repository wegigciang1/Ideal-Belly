/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ciang
 */
public class DaftarMakanan {

    /**
     * @return the deskripsi
     */
    public String getDeskripsi() {
        return deskripsi.get();
    }

    /**
     * @param deskripsi the deskripsi to set
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi.set(deskripsi);
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
     * @return the nama_makanan
     */
    public String getNama_makanan() {
        return nama_makanan.get();
    }

    /**
     * @param nama_makanan the nama_makanan to set
     */
    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan.set(nama_makanan);
    }

    /**
     * @return the path_img
     */
    public String getPath_img() {
        return path_img.get();
    }

    /**
     * @param path_img the deskripsi to set
     */
    public void setPath_img(String path_img) {
        this.path_img.set(path_img);
    }

    /**
     * @return the kalori
     */
    public double getKalori() {
        return kalori.get();
    }

    /**
     * @param kalori the kalori to set
     */
    public void setKalori(double kalori) {
        this.kalori.set(kalori);
    }
    
   
    /**
     * @return the id
     */
   
    private SimpleIntegerProperty id= new SimpleIntegerProperty();
    private SimpleStringProperty nama_makanan= new SimpleStringProperty();
    private SimpleDoubleProperty kalori= new SimpleDoubleProperty();
    private SimpleStringProperty deskripsi= new SimpleStringProperty();
    private SimpleStringProperty path_img= new SimpleStringProperty();

}