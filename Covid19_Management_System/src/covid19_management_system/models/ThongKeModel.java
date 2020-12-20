/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_management_system.models;

/**
 *
 * @author HieuPhung
 */
public class ThongKeModel {
    private int soNhanKhau;

    public int getSoNhanKhau() {
        return soNhanKhau;
    }

    public void setSoNhanKhau(int soNhanKhau) {
        this.soNhanKhau = soNhanKhau;
    }

    public int getDaKhaiBao() {
        return daKhaiBao;
    }

    public void setDaKhaiBao(int daKhaiBao) {
        this.daKhaiBao = daKhaiBao;
    }

    public int getChuaKhaiBao() {
        return chuaKhaiBao;
    }

    public void setChuaKhaiBao(int chuaKhaiBao) {
        this.chuaKhaiBao = chuaKhaiBao;
    }

    public int getTuVungDich() {
        return tuVungDich;
    }

    public void setTuVungDich(int tuVungDich) {
        this.tuVungDich = tuVungDich;
    }

    public int getTxCovid() {
        return txCovid;
    }

    public void setTxCovid(int txCovid) {
        this.txCovid = txCovid;
    }

    public int getMacCovid() {
        return macCovid;
    }

    public void setMacCovid(int macCovid) {
        this.macCovid = macCovid;
    }

    public int getCachLy() {
        return cachLy;
    }

    public void setCachLy(int cachLy) {
        this.cachLy = cachLy;
    }

    public int getCLTaiNha() {
        return CLTaiNha;
    }

    public void setCLTaiNha(int CLTaiNha) {
        this.CLTaiNha = CLTaiNha;
    }

    public int getCLTapTrung() {
        return CLTapTrung;
    }

    public void setCLTapTrung(int CLTapTrung) {
        this.CLTapTrung = CLTapTrung;
    }
    private int daKhaiBao;
    private int chuaKhaiBao;
    private int tuVungDich;
    private int txCovid;
    private int macCovid;
    private int cachLy;
    private int CLTaiNha;
    private int CLTapTrung;
    
}
