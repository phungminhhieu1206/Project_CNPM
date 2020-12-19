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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuPhung
 */
public class EditCachLyController {
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    public CachLyModel searchAllInfoCachLy(int IDNhanKhau) {
        CachLyModel cachLyModel = new CachLyModel();
        cachLyModel.setIDNhanKhau(IDNhanKhau);
        
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `ngayKhaiCL`, `loaiCL`, `mucDoCL`, `ngayBatDauCL`, `diaChiCL`, `soPhongCL`, `soGiuongCL`, `tenNgCungPhCL` FROM `cach_ly` WHERE `IDNhanKhau`=?";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);

            preparedStatement.setInt(1, cachLyModel.getIDNhanKhau());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cachLyModel.setNgayKhaiCL(resultSet.getDate(1));
                cachLyModel.setLoaiCL(resultSet.getInt(2));
                cachLyModel.setMucDoCL(resultSet.getInt(3));
                cachLyModel.setNgayBatDauCL(resultSet.getDate(4));
                cachLyModel.setDiaChiCL(resultSet.getString(5).trim());
                cachLyModel.setSoPhongCL(resultSet.getString(6).trim());
                cachLyModel.setSoGiuongCL(resultSet.getString(7).trim());
                cachLyModel.setTenNgCungPhCL(resultSet.getString(8).trim());
                
                return cachLyModel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditCachLyController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
        return null;
    }
    
    public TestCovidModel searchInfoLastTestCovid(int IDNhanKhau) {
        TestCovidModel testCovidModel = new TestCovidModel();
        testCovidModel.setIDNhanKhau(IDNhanKhau);
        
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT `ngayTest`, `lanTest`, `hinhThucTest`, `diaDiemTest`, `ketQuaTest` FROM `test_covid` WHERE `IDNhanKhau`=? ORDER BY `lanTest` DESC LIMIT 1";
        try {
            preparedStatement = my_connection.createConnection().prepareStatement(query);
            preparedStatement.setInt(1, testCovidModel.getIDNhanKhau());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                testCovidModel.setNgayTest(resultSet.getDate(1));
                testCovidModel.setLanTest(resultSet.getInt(2));
                testCovidModel.setHinhThucTest(resultSet.getInt(3));
                testCovidModel.setDiaDiemTest(resultSet.getString(4));
                testCovidModel.setKetQuaTest(resultSet.getInt(5));
                
                return testCovidModel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditCachLyController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }  
        return null;
    }
    
}
