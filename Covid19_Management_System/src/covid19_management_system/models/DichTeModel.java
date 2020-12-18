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
public class DichTeModel {
    private int ID;
    private int ID_PERSON;
    private Date ngayKhaiDichTe;
    private String ngayKhaiDichTeStr;
    private int txCovid;
    private int tuVungDich;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_PERSON() {
        return ID_PERSON;
    }

    public void setID_PERSON(int ID_PERSON) {
        this.ID_PERSON = ID_PERSON;
    }

    public Date getNgayKhaiDichTe() {
        return ngayKhaiDichTe;
    }

    public void setNgayKhaiDichTe(Date ngayKhaiDichTe) {
        this.ngayKhaiDichTe = ngayKhaiDichTe;
    }

    public String getNgayKhaiDichTeStr() {
        return ngayKhaiDichTeStr;
    }

    public void setNgayKhaiDichTeStr(String ngayKhaiDichTetStr) {
        this.ngayKhaiDichTeStr = ngayKhaiDichTetStr;
    }

    public int getTxCovid() {
        return txCovid;
    }

    public void setTxCovid(int txCovid) {
        this.txCovid = txCovid;
    }

    public int getTuVungDich() {
        return tuVungDich;
    }

    public void setTuVungDich(int tuVungDich) {
        this.tuVungDich = tuVungDich;
    }
    
    
    
}
