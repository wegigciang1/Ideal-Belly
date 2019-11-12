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
public class BMI {
    public static double countBMI(double tinggi_badan, double berat_badan){
        return berat_badan/Math.pow((tinggi_badan/100),2);
    }
    
    public static String getStatusBMI(double bmi){
        String status = "";
        if(bmi < 17)
            status = "kurus";
        else if(bmi >= 17 && bmi < 23)
            status = "normal";
        else if(bmi >= 23 && bmi <= 27)
            status = "kegemukan";
        else if(bmi >= 27)
            status = "obesitas";
        
        return status;
    }
}
