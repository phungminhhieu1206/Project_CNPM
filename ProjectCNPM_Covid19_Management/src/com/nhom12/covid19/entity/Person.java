/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom12.covid19.entity;

import com.nhom12.covid19.MY_CONNECTION;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 * 
 * @author PhamVanHanh
 */
public class Person {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // method to search person by cmt
    public boolean searchPerson(String cmt){
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `people` WHERE `cmt`=?";
        
        try {
            
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            ps.setString(1, cmt);
            rs = ps.executeQuery();
            
            // chưa đúng lắm -> hiểu thêm
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // method to add new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address){
        
        
        return true;
        
    }
    
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {
          
        return true;
           
    }
    public boolean removePerson(int idPerson){
        
        return true;
    }
    
}
