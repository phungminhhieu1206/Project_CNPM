/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.entity;

import covid19_management_system.MY_CONNECTION;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuPhung
 */

// khai báo dịch tễ form
public class DichTe {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    /* 1 - Các phương thức ADD đối tượng cho các bảng*/
    public boolean addDichTe(int idPerson, String ngayKhai, String txBenh, String tuVungDich) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `dich_te`(`id_person`, `ngay_khai`, `tx_benh`, `tu_vung_dich`) VALUES (?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            ps.setString(2, ngayKhai);
            ps.setString(3, txBenh);
            ps.setString(4, tuVungDich);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean addTrieuChung(int idPerson, int trieuChung) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `khai_trieu_chung`(`id_person`, `trieu_chung`) VALUES (?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            ps.setInt(2, trieuChung);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean addBenh(int idPerson, int idBenh) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `khai_benh`(`id_person`, `ma_benh`) VALUES (?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            ps.setInt(2, idBenh);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    /* 2 - Các phương thức DELETE đối tượng cho các bảng*/
    public boolean removeDichTe(int id) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `dich_te` WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, id);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean removeTrieuChung(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `khai_trieu_chung` WHERE `id_person`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean removeBenh(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `khai_benh` WHERE `id_person`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    /* Các phương thức chỉnh sửa */
    
    public boolean editDichTe(int idPerson, String ngayKhai, String txBenh, String tuVungDich) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `dich_te` SET `ngay_khai`=?,`tx_benh`=?,`tu_vung_dich`=? WHERE `id_person`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
//            // theo thu tu dau ?
            ps.setString(1, ngayKhai);
            ps.setString(2, txBenh);
            ps.setString(3, tuVungDich);
            ps.setInt(4, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DichTe.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
}
