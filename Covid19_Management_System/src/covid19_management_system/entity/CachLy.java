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
 * @author winwin.thathang
 */
public class CachLy {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    /* 1 - Các phương thức ADD đối tượng cho các bảng*/
    public boolean addCachLy(int idPerson, String ngayKhaiCL, int type, int level, String dateStart, String addressCL, int roomN, int bedN, String nameRoommate) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `cach_ly`(`id_person`, `ngay_khai_cl`, `type`, `level`, `cl_date_start`, `cl_address`, `room_num`, `bed_num`, `roommate_name`) VALUES (?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            ps.setString(2, ngayKhaiCL);
            ps.setInt(3, type);
            ps.setInt(4, level);
            ps.setString(5, dateStart);
            ps.setString(6, addressCL);
          /*    ps.setInt(1, idPerson);
            ps.setString(2, ngayKhaiCL);
            ps.setInt(3, type);
            ps.setInt(4, level);
            ps.setString(5, dateStart);
            ps.setString(6, addressCL);*/
            ////
            ps.setInt(7, roomN);
            ps.setInt(8, bedN);
            ps.setString(9, nameRoommate);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CachLy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean addTestCovid(int idPerson, String dateTest, int timeTest, int formTest, String addressTest, int resultTest) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `test_covid`(`id_person`, `date_test`, `times_test`, `forms_test`, `address_test`, `result_test`) VALUES (?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setInt(4, formTest);
            ps.setString(5, addressTest);
            ps.setInt(6, resultTest);
            ps.setInt(1, idPerson);
            ps.setString(2, dateTest);
            ps.setInt(3, timeTest);
           //// thu tu nao
            /* ps.setInt(4, formTest);
            ps.setString(5, addressTest);
            ps.setInt(6, resultTest);
            ps.setInt(1, idPerson);
            ps.setString(2, dateTest);
            ps.setInt(3, timeTest); */
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CachLy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    /* 2 - Các phương thức DELETE đối tượng cho các bảng*/
    public boolean removeCachLy(int id) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `cach_ly` WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, id);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CachLy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean removeTest(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `test_covid` WHERE `id_person`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CachLy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean removeOneTest(int timeTest) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `test_covid` WHERE `times_test`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(deleteQuery);
            
            // theo thu tu dau ?
            ps.setInt(1, timeTest);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CachLy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
   
    
    public boolean editCachLy(int idPerson, String ngayKhaiCL, int type, int level, String dateStart, String addressCL, int roomN, int bedN, String nameRoommate) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `cach_ly` SET `ngay_khai_cl`=?,`type`=?,`level`=?,`cl_date_start`=?,`cl_address`=?,`room_num`=?,`bed_num`=?,`roommate_name`=? WHERE `id_person`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
//            // theo thu tu dau ?
            ps.setString(1, ngayKhaiCL);
            ps.setInt(2, type);
            ps.setInt(3, level);
            ps.setString(4, dateStart);
            ps.setString(5, addressCL);
            ps.setInt(6, roomN);
            ps.setInt(7, bedN);
            ps.setString(8, nameRoommate);
            ps.setInt(9, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CachLy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
     public boolean sua(int idPerson, String ngayKhaiCL, int type, int level, String dateStart, String addressCL, int roomN, int bedN, String nameRoommate) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `cach_ly` SET `ngay_khai_cl`=?,`type`=?,`level`=?,`cl_date_start`=?,`cl_address`=?,`room_num`=?,`bed_num`=?,`roommate_name`=? WHERE `id_person`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
//            // theo thu tu dau ?
            ps.setString(1, ngayKhaiCL);
            ps.setInt(2, type);
            ps.setInt(3, level);
            ps.setString(4, dateStart);
            ps.setString(5, addressCL);
            ps.setInt(6, roomN);
            ps.setInt(7, bedN);
            ps.setString(8, nameRoommate);
            ps.setInt(9, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CachLy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
}
