/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpl_ceria.ideal.belly.model;



/**
 *
 * @author nathania
 */
public class CountUserBody {
    // BMI = body mass index
    public static double countBMI(double tinggi_badan, double berat_badan){
        return berat_badan/Math.pow((tinggi_badan/100),2);
    }
    
    // BMR = basal metabolic rate
    public static double countBMR(double tinggi_badan, double berat_badan, double umur, String jenis_kelamin){
        if(jenis_kelamin.equals("laki-laki")){
            return 66.4730 + (13.7516 * berat_badan) + (5.0033 * tinggi_badan) - (6.7550 * umur);
        }
        else if(jenis_kelamin.equals("perempuan")){
            return 665.0955 + (9.5634 * berat_badan) + (1.8496 * tinggi_badan) - (4.6756 * umur);
        }
        return 0;
    }
    
    // TEE = total energy expenditure
    public static double countTEE(double bmr, String levelAktifitasFisik){
        // Tidak Aktif
        switch (levelAktifitasFisik) {
            case "tidak aktif":
                return bmr * 1.2;
            case "cukup aktif":
                return bmr * 1.375;
            case "aktif":
                return bmr * 1.55;
            case "sangat aktif":
                return bmr * 1.725;
            default:
                break;
        }
        return 0;
    }
    
    public static String getStatusBMI(double bmi){
        String status = "";
        if(bmi < 18.5)
            status = "Kekurangan berat badan (kurus)";
        else if(bmi >= 17 && bmi < 23)
            status = "Normal (ideal)s";
        else if(bmi >= 23 && bmi <= 27)
            status = "Kelebihan Berat Badan";
        else if(bmi >= 27)
            status = "Kegemukan (Obesitas)";
        
        return status;
    }
}
