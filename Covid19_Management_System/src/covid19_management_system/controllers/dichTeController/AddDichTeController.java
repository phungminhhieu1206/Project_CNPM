/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.dichTeController;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.controllers.nhankhauController.EditNhanKhauController;
import covid19_management_system.models.DichTeModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuPhung
 */
public class AddDichTeController {

    MY_CONNECTION my_connection = new MY_CONNECTION();

    // xet xem có nhân khẩu với CMT kia không ?
    public boolean searchInFromCMT(String chungMinhThu) {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM `nhan_khau` WHERE `chungMinhThu`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setString(1, chungMinhThu);
            try {
                if (preparedStatement.executeQuery().next()) {
                    preparedStatement.close();
                    my_connection.getConnection().close();
                    return true;
                }
            } catch (SQLException e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public boolean addDichTe(DichTeModel temp) throws SQLException {
        DichTeModel dichTeModel = new DichTeModel();
        dichTeModel = temp;

        PreparedStatement preparedStatement;
        String query = "INSERT INTO `dich_te`(`id_person`, `ngay_khai`, `tx_benh`, `tu_vung_dich`) VALUES (?,?,?,?)";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);

            // theo thu tu dau ?
            preparedStatement.setInt(1, dichTeModel.getID_PERSON());
            java.sql.Date ngayKhaiDichTe = new java.sql.Date(dichTeModel.getNgayKhaiDichTe().getTime());
            preparedStatement.setDate(2, ngayKhaiDichTe);
            preparedStatement.setInt(3, dichTeModel.getTxCovid());
            preparedStatement.setInt(4, dichTeModel.getTuVungDich());
//            return (preparedStatement.executeUpdate() > 0);
            if (preparedStatement.executeUpdate() > 0) {
                preparedStatement.close();
                my_connection.getConnection().close();
                return true;
            };

        } catch (SQLException ex) {
            Logger.getLogger(AddDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            my_connection.getConnection().close();
            return false;
        }
        return false;
    }

    public boolean addTrieuChung(int idPerson, int trieuChung) throws SQLException {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `khai_trieu_chung`(`id_person`, `trieu_chung`) VALUES (?,?)";

        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);

            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            ps.setInt(2, trieuChung);

//            return (ps.executeUpdate() > 0);
            if (ps.executeUpdate() > 0) {
                ps.close();
                my_connection.getConnection().close();
                return true;
            };

        } catch (SQLException ex) {
            Logger.getLogger(AddDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            my_connection.getConnection().close();
            return false;
        }
        return false;

    }

    public boolean addBenh(int idPerson, int idBenh) throws SQLException {

        PreparedStatement ps;
        String addQuery = "INSERT INTO `khai_benh`(`id_person`, `ma_benh`) VALUES (?,?)";

        try {
            // add client on mysql database
            ps = my_connection.createConnection().prepareStatement(addQuery);

            // theo thu tu dau ?
            ps.setInt(1, idPerson);
            ps.setInt(2, idBenh);

//            return (ps.executeUpdate() > 0);
            if (ps.executeUpdate() > 0) {
                ps.close();
                my_connection.getConnection().close();
                return true;
            };

        } catch (SQLException ex) {
            Logger.getLogger(AddDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            my_connection.getConnection().close();
            return false;
        }
        return false;

    }

}
