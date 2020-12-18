/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.controllers.cachlyController;

import covid19_management_system.MY_CONNECTION;
import covid19_management_system.models.CachLyModel;
import covid19_management_system.models.TestCovidModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuPhung
 */
public class AddCachLyController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public boolean addCachLy(CachLyModel temp) {
        CachLyModel cachLyModel = new CachLyModel();
        cachLyModel = temp;
        
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `cach_ly`(`IDNhanKhau`, `ngayKhaiCL`, `loaiCL`, `mucDoCL`, `ngayBatDauCL`, `diaChiCL`, `soPhongCL`, `soGiuongCL`, `tenNgCungPhCL`) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            
            preparedStatement.setInt(1, cachLyModel.getIDNhanKhau());
            //date
            java.sql.Date ngayKhaiCL = new java.sql.Date(cachLyModel.getNgayKhaiCL().getTime());
            preparedStatement.setDate(2, ngayKhaiCL);
            preparedStatement.setInt(3, cachLyModel.getLoaiCL());
            preparedStatement.setInt(4, cachLyModel.getMucDoCL());
            //date
            java.sql.Date ngayBatDauCL = new java.sql.Date(cachLyModel.getNgayBatDauCL().getTime());
            preparedStatement.setDate(5, ngayBatDauCL);
            preparedStatement.setString(6, cachLyModel.getDiaChiCL());
            preparedStatement.setString(7, cachLyModel.getSoGiuongCL());
            preparedStatement.setString(8, cachLyModel.getSoGiuongCL());
            preparedStatement.setString(9, cachLyModel.getTenNgCungPhCL());
            
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(AddCachLyController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
    public boolean addTestCovid(TestCovidModel temp) {
        TestCovidModel testCovidModel = new TestCovidModel();
        testCovidModel = temp;
        
        PreparedStatement preparedStatement;
        String query = "INSERT INTO `test_covid`(`IDNhanKhau`, `ngayTest`, `lanTest`, `hinhThucTest`, `diaDiemTest`, `ketQuaTest`) VALUES (?,?,?,?,?,?)";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            
            preparedStatement.setInt(1, testCovidModel.getIDNhanKhau());
            //date
            java.sql.Date ngayTest = new java.sql.Date(testCovidModel.getNgayTest().getTime());
            preparedStatement.setDate(2, ngayTest);
            preparedStatement.setInt(3, testCovidModel.getLanTest());
            preparedStatement.setInt(4, testCovidModel.getHinhThucTest());
            preparedStatement.setString(5, testCovidModel.getDiaDiemTest());
            preparedStatement.setInt(6, testCovidModel.getKetQuaTest());
            
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            Logger.getLogger(AddCachLyController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
   
    
}
