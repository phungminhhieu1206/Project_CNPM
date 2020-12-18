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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author winwin.thathang
 */
public class ShowTable {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    

    // create a function to populate the jTable with all the clients in the database
    public void fillPeopleJTable(JTable table) {
        
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `people`";
        
        try {
            
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] col;
            
            while(rs.next()){
                
                col = new Object[11]; // because we have 5 fileds of clients in mysql database
                col[0] = rs.getInt(1);
                col[1] = rs.getString(2);
                col[2] = rs.getString(3);
                col[3] = rs.getString(4);
                col[4] = rs.getString(5);
                col[5] = rs.getString(6);
                col[6] = rs.getString(7);
                col[7] = rs.getString(8);
                col[8] = rs.getString(9);
                col[9] = rs.getString(10);
                col[10] = rs.getString(11);
                
                tableModel.addRow(col);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ShowTable.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
    
    
}
