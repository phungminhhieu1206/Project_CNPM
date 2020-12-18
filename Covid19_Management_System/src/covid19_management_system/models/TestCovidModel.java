/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.models;

import java.util.Date;

/**
 *
 * @author HieuPhung
 */
public class TestCovidModel {
    private int ID;
    private int IDNhanKhau;
    private Date ngayTest;
    private String ngayTestStr;
    private int lanTest;
    private int hinhThucTest;
    private String diaDiemTest;
    private int ketQuaTest;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDNhanKhau() {
        return IDNhanKhau;
    }

    public void setIDNhanKhau(int IDNhanKhau) {
        this.IDNhanKhau = IDNhanKhau;
    }

    public Date getNgayTest() {
        return ngayTest;
    }

    public void setNgayTest(Date ngayTest) {
        this.ngayTest = ngayTest;
    }

    public String getNgayTestStr() {
        return ngayTestStr;
    }

    public void setNgayTestStr(String ngayTestStr) {
        this.ngayTestStr = ngayTestStr;
    }

    public int getLanTest() {
        return lanTest;
    }

    public void setLanTest(int lanTest) {
        this.lanTest = lanTest;
    }

    public int getHinhThucTest() {
        return hinhThucTest;
    }

    public void setHinhThucTest(int hinhThucTest) {
        this.hinhThucTest = hinhThucTest;
    }

    public String getDiaDiemTest() {
        return diaDiemTest;
    }

    public void setDiaDiemTest(String diaDiemTest) {
        this.diaDiemTest = diaDiemTest;
    }

    public int getKetQuaTest() {
        return ketQuaTest;
    }

    public void setKetQuaTest(int ketQuaTest) {
        this.ketQuaTest = ketQuaTest;
    }
    
    
    
}
