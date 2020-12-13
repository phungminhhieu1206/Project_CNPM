/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom12.covid19;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Users {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to add a client
    public boolean addUser(String username, String password) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `users`(`username`, `password`) VALUES (?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
}
