/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.entity;

import covid19_management_system.MY_CONNECTION;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author HieuPhung
 */

// the Person Class
public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
    /*public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
        
    }public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
        
    }public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
        
    }public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
        
    }public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
        
    }public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
        
    }public class Person {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // create a function to search person from cmt ?
    public boolean searchPerson(String cmt) {
        
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
    
    // create a function to add a new person
    public boolean addPerson(String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `people`(`name`, `birthday`, `gender`, `bhyt`, `bhyt_num`, `cmt`, `ho_khau`, `phone`, `email`, `address`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean editPerson(int idPerson, String name, String birthday, String gender, String bhyt, String bhyt_num, String cmt, String ho_khau, String phone, String email, String address) {

        PreparedStatement ps;
        String updateQuery = "UPDATE `people` SET `name`=?,`birthday`=?,`gender`=?,`bhyt`=?,`bhyt_num`=?,`cmt`=?,`ho_khau`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(updateQuery);
            
            // theo thu tu dau ?
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, gender);
            ps.setString(4, bhyt);
            ps.setString(5, bhyt_num);
            ps.setString(6, cmt);
            ps.setString(7, ho_khau);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setString(10, address);
            ps.setInt(11, idPerson);
            
            return (ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    // create a function to add a new person
    public boolean removePerson(int idPerson) {

        PreparedStatement ps;
        String deleteQuery = "DELETE FROM `people` WHERE `id`=?";
        
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
        
    }*/
    
}
