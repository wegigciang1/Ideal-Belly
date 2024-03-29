/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import rpl_ceria.ideal.belly.model.DaftarMakanan;

/**
 *
 * @author ciang
 */
public class DaftarMakananDAO {
    public static DaftarMakanan searchMakanan(int id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM  daftarMakanan WHERE id='" + id + "'";
        try {
            ResultSet rsMkn = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            DaftarMakanan mkn = getMakananFromResultSet(rsMkn);
            return mkn;
        } catch (SQLException e) {
            System.out.println("Sedang mencari makanan dengan id " + id + ", error terjadi: " + e);
            throw e;
        }
    }
    
    //Tambahan Iterasi3
    public static DaftarMakanan searchMakananByName(String nama_makanan) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM  daftarMakanan WHERE nama_makanan='" + nama_makanan + "'";
        try {
            ResultSet rsMkn = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            DaftarMakanan mkn = getMakananFromResultSet(rsMkn);
            return mkn;
        } catch (SQLException e) {
            System.out.println("Sedang mencari makanan dengan nama " + nama_makanan + ", error terjadi: " + e);
            throw e;
        }
    }
    //

    private static DaftarMakanan getMakananFromResultSet(ResultSet rs) throws SQLException {
        DaftarMakanan mkn = null;
        if (rs.next()) {
            mkn = new DaftarMakanan();
            mkn.setId(rs.getInt("id"));
            mkn.setNama_makanan(rs.getString("nama_makanan"));
            mkn.setKalori(rs.getDouble("kalori"));
            mkn.setDeskripsi(rs.getString("deskripsi"));
            mkn.setPath_img(rs.getString("path_img"));
        }
        return mkn;
    }

    public static ObservableList<DaftarMakanan> searchMakanans(double tee) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM daftarMakanan WHERE kalori<='" + tee + "'";
        try {
            ResultSet rsMkn = DBUtil.getInstance().dbExecuteQuery(selectStmt);
            ObservableList<DaftarMakanan> newList;
            newList = getMakananList(rsMkn);
            return newList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e); //Return exception
            throw e;
        }
    }

    private static ObservableList<DaftarMakanan> getMakananList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<DaftarMakanan> mknList = FXCollections.observableArrayList();
        while (rs.next()) {
            DaftarMakanan mkn = new DaftarMakanan();
            mkn.setId(rs.getInt("id"));
            mkn.setNama_makanan(rs.getString("nama_makanan"));
            mkn.setKalori(rs.getDouble("kalori"));
            mkn.setDeskripsi(rs.getString("deskripsi"));
            mkn.setPath_img(rs.getString("path_img"));
            
            mknList.add(mkn);
        }
        Random rand = new Random(); 
		// create a temporary list for storing 
		// selected element 
                // untuk menentukan mau brp banyak yang di random
                int jumlah_random= 4;
		ObservableList<DaftarMakanan> newList = FXCollections.observableArrayList(); 
		for (int i = 0; i < jumlah_random; i++) { 

			// take a raundom index between 0 to size 
			// of given List 
			int randomIndex = rand.nextInt(mknList.size()); 

			// add element in temporary list 
                        newList.add(mknList.get(randomIndex));
			//newList.add(mknList.get(randomIndex)); 
		} 
	return newList; 
    }
    
    public static void updateMakanan(String id, DaftarMakanan mkn) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE daftarMakanan SET id='" + mkn.getId() + "',"
                + "nama_makanan = '" + mkn.getNama_makanan() + "',"
                + "kalori = '" + mkn.getKalori() + ","
                + "path_img = '" + mkn.getPath_img() + "',"
                + "deskripsi = '" + mkn.getDeskripsi() + "' WHERE id='" + id + "'";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void addMakanan(DaftarMakanan mkn) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO daftarMakanan (id, nama_makanan, kalori, path_img, deskripsi) VALUES (null, '"
                + mkn.getNama_makanan() + "',"
                + mkn.getKalori() + ",'"
                + mkn.getPath_img() + "','"
                + mkn.getDeskripsi() + "')";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void deleteMakananWithId(String id) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM daftarMakanan WHERE id='" + id + "'";
        try {
            DBUtil.getInstance().dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

   

}