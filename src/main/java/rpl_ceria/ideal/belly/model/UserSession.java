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
public class UserSession {
    private static User userNow;
    
    public static void setUserSession(User usr){
        userNow = usr;
    }
    
    public static User getUserSession(){
        return userNow;
    }
}
