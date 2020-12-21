/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.dichTeController;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.controllers.nhankhauController.ShowTableNhanKhauController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author HieuPhung
 * @author HieuPhung
 */
public class ShowTableDichTeController {

    MY_CONNECTION my_connection = new MY_CONNECTION();

    public void showDichTe(JTable table) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        int IDNhanKhau;
        String query = "SELECT `id_person`, `ngay_khai`, `tx_benh`, `tu_vung_dich` FROM `dich_te` WHERE 1 ORDER BY `id_person` ASC";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            Object[] row;
            while (resultSet.next()) {
                IDNhanKhau = resultSet.getInt(1);
                row = new Object[11];
                for (int i = 0; i < 11; i++) {
                    row[i] = 0;
                }

                // - search thông tin cá nhân
                PreparedStatement preparedStatementNK;
                ResultSet resultSetNK;
                String queryNK = "SELECT `ID`, `hoTen`, `ngaySinh`, `gioiTinh`, `diaChi`, `soDienThoai` FROM `nhan_khau` WHERE `ID`=?";
                try {
                    preparedStatementNK = my_connection.createConnection().prepareStatement(queryNK);
                    preparedStatementNK.setInt(1, IDNhanKhau);
                    resultSetNK = preparedStatementNK.executeQuery();

                    while (resultSetNK.next()) {
                        row[0] = resultSetNK.getInt(1);
                        row[1] = resultSetNK.getString(2);
                        // ngaySinh
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        row[2] = dateFormat.format(resultSetNK.getDate(3));
                        // gioiTinh
                        int gioiTinh = resultSetNK.getInt(4);
                        switch (gioiTinh) {
                            case 0:
                                row[3] = "";
                                break;
                            case 1:
                                row[3] = "Nam";
                                break;
                            case 2:
                                row[3] = "Nữ";
                                break;
                            case 3:
                                row[3] = "Khác";
                                break;
                            default:
                                break;
                        }
                        row[4] = resultSetNK.getString(5);
                        row[5] = resultSetNK.getString(6);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // - kết thúc thông tin cá nhân

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                row[6] = dateFormat.format(resultSet.getDate(2));
//                System.out.println("hello");

                int txCovid = resultSet.getInt(3);
                switch (txCovid) {
                    case 0:
                        row[7] = "Không";
                        break;
                    case 1:
                        row[7] = "Có";
                        break;
                    default:
                        break;
                }

                int tuVungDich = resultSet.getInt(4);
                switch (tuVungDich) {
                    case 0:
                        row[8] = "Không";
                        break;
                    case 1:
                        row[8] = "Có";
                        break;
                    default:
                        break;
                }
                
                // - search thông tin trieu chung
                PreparedStatement preparedStatementTC;
                ResultSet resultSetTC;
                String trieuChung = "";
                String queryTC = "SELECT `trieu_chung` FROM `khai_trieu_chung` WHERE `id_person`=?";
                try {
                    preparedStatementTC = my_connection.createConnection().prepareStatement(queryTC);
                    preparedStatementTC.setInt(1, IDNhanKhau);
                    resultSetTC = preparedStatementTC.executeQuery();

                    while (resultSetTC.next()) {
                        trieuChung += setTrieuChung(resultSetTC.getInt(1)) + ", ";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                row[9] = trieuChung;
                // - kết thúc thông tin triệu chứng
                
                // - search thông tin bệnh
                PreparedStatement preparedStatementBenh;
                ResultSet resultSetBenh;
                String benh = "";
                String queryBenh = "SELECT `ma_benh` FROM `khai_benh` WHERE `id_person`=?";
                try {
                    preparedStatementBenh = my_connection.createConnection().prepareStatement(queryBenh);
                    preparedStatementBenh.setInt(1, IDNhanKhau);
                    resultSetBenh = preparedStatementBenh.executeQuery();

                    while (resultSetBenh.next()) {
                        benh += setBenh(resultSetBenh.getInt(1)) + ", ";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                row[10] = benh;
                // - kết thúc thông tin bệnh

                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showDichTeWithCMT(JTable table, String CMT) {
        // ---------- search ID nhân khẩu -------------------
        int IDNhanKhau = 0;
        PreparedStatement ps;
        ResultSet rs;
        String querySearch = "SELECT `ID` FROM `nhan_khau` WHERE `chungMinhThu`=?";
        try {
            ps = my_connection.createConnection().prepareStatement(querySearch);
            ps.setString(1, CMT);
            rs = ps.executeQuery();
            
            while(rs.next()){
                IDNhanKhau = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (IDNhanKhau == 0) return;
        // -------------- đã có ID nhân khẩu -----------------------
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `id_person`, `ngay_khai`, `tx_benh`, `tu_vung_dich` FROM `dich_te` WHERE `id_person`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, IDNhanKhau);
            resultSet = preparedStatement.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            Object[] row;
            while (resultSet.next()) {
                row = new Object[11];

                // - search thông tin cá nhân
                PreparedStatement preparedStatementNK;
                ResultSet resultSetNK;
                String queryNK = "SELECT `ID`, `hoTen`, `ngaySinh`, `gioiTinh`, `diaChi`, `soDienThoai` FROM `nhan_khau` WHERE `ID`=?";
                try {
                    preparedStatementNK = my_connection.createConnection().prepareStatement(queryNK);
                    preparedStatementNK.setInt(1, IDNhanKhau);
                    resultSetNK = preparedStatementNK.executeQuery();

                    while (resultSetNK.next()) {
                        row[0] = resultSetNK.getInt(1);
                        row[1] = resultSetNK.getString(2);
                        // ngaySinh
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        row[2] = dateFormat.format(resultSetNK.getDate(3));
                        // gioiTinh
                        int gioiTinh = resultSetNK.getInt(4);
                        switch (gioiTinh) {
                            case 0:
                                row[3] = "";
                                break;
                            case 1:
                                row[3] = "Nam";
                                break;
                            case 2:
                                row[3] = "Nữ";
                                break;
                            case 3:
                                row[3] = "Khác";
                                break;
                            default:
                                break;
                        }
                        row[4] = resultSetNK.getString(5);
                        row[5] = resultSetNK.getString(6);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // - kết thúc thông tin cá nhân

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                row[6] = dateFormat.format(resultSet.getDate(2));
//                System.out.println("hello");

                int txCovid = resultSet.getInt(3);
                switch (txCovid) {
                    case 0:
                        row[7] = "Không";
                        break;
                    case 1:
                        row[7] = "Có";
                        break;
                    default:
                        break;
                }

                int tuVungDich = resultSet.getInt(4);
                switch (tuVungDich) {
                    case 0:
                        row[8] = "Không";
                        break;
                    case 1:
                        row[8] = "Có";
                        break;
                    default:
                        break;
                }
                
                // - search thông tin trieu chung
                PreparedStatement preparedStatementTC;
                ResultSet resultSetTC;
                String trieuChung = "";
                String queryTC = "SELECT `trieu_chung` FROM `khai_trieu_chung` WHERE `id_person`=?";
                try {
                    preparedStatementTC = my_connection.createConnection().prepareStatement(queryTC);
                    preparedStatementTC.setInt(1, IDNhanKhau);
                    resultSetTC = preparedStatementTC.executeQuery();

                    while (resultSetTC.next()) {
                        trieuChung += setTrieuChung(resultSetTC.getInt(1)) + ", ";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                row[9] = trieuChung;
                // - kết thúc thông tin triệu chứng
                
                // - search thông tin bệnh
                PreparedStatement preparedStatementBenh;
                ResultSet resultSetBenh;
                String benh = "";
                String queryBenh = "SELECT `ma_benh` FROM `khai_benh` WHERE `id_person`=?";
                try {
                    preparedStatementBenh = my_connection.createConnection().prepareStatement(queryBenh);
                    preparedStatementBenh.setInt(1, IDNhanKhau);
                    resultSetBenh = preparedStatementBenh.executeQuery();

                    while (resultSetBenh.next()) {
                        benh += setBenh(resultSetBenh.getInt(1)) + ", ";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                row[10] = benh;
                // - kết thúc thông tin bệnh

                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String setTrieuChung(int key) {
        String trieuChung = "";
        switch(key) {
            case 1:
                trieuChung = "Sốt";
                break;
            case 2:
                trieuChung = "Ho";
                break;
            case 3:
                trieuChung = "Khó thở";
                break;
            case 4:
                trieuChung = "Viêm phổi";
                break;
            case 5:
                trieuChung = "Đau họng";
                break;
            case 6:
                trieuChung = "Mệt mỏi";
                break;
            case 7:
                trieuChung = "Buồn nôn";
                break;
            case 8:
                trieuChung = "Tiêu chảy";
                break;
            case 9:
                trieuChung = "Xuất huyết ngoài da";
                break;
            default:
                break;
            
        }
        return trieuChung;
    }
    
    public String setBenh(int key) {
        String benh = "";
        switch(key) {
            case 1:
                benh = "Gan";
                break;
            case 2:
                benh = "Máu";
                break;
            case 3:
                benh = "Phổi";
                break;
            case 4:
                benh = "Thận";
                break;
            case 5:
                benh = "Tim";
                break;
            case 6:
                benh = "Huyết áp";
                break;
            case 7:
                benh = "HIV/AIDS";
                break;
            case 8:
                benh = "Tiểu đường";
                break;
            case 9:
                benh = "Ung thư";
                break;
            default:
                break;
            
        }
        return benh;
    }

    // check IDNhanKhau có trong dich_te không ?
    
    public boolean checkIdDichTe(int IDNhanKhau) {
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM `dich_te` WHERE `id_person`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, IDNhanKhau);
            try {
                if (preparedStatement.executeQuery().next()) {
                    return true;
                }
            } catch (SQLException e) {}
        } catch (SQLException ex) {
            Logger.getLogger(ShowTableDichTeController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
}
