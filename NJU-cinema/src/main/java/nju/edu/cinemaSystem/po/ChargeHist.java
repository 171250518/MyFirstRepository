package nju.edu.cinemaSystem.po;

import java.sql.Timestamp;

public class ChargeHist {
    private int id;
    private int charge;
    private float balance_befo;
    private float balance_after;
    private Timestamp date;
    private int vip_card_id;

    public ChargeHist(int id,int charge,float balance_befo, float balance_after,Timestamp date,int vip_card_id){
        this.id=id;
        this.charge=charge;
        this.balance_befo=balance_befo;
        this.balance_after=balance_after;
        this.date=date;
        this.vip_card_id=vip_card_id;
    }
    public ChargeHist(int charge,float balance_befo, float balance_after,Timestamp date,int vip_card_id){
        this.charge=charge;
        this.balance_befo=balance_befo;
        this.balance_after=balance_after;
        this.date=date;
        this.vip_card_id=vip_card_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getCharge() {
        return charge;
    }

    public void setBalance_befo(float balance_befo) {
        this.balance_befo = balance_befo;
    }

    public float getBalance_befo() {
        return balance_befo;
    }

    public void setBalance_after(float balance_after) {
        this.balance_after = balance_after;
    }

    public float getBalance_after() {
        return balance_after;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setVip_card_id(int vip_card_id) {
        this.vip_card_id = vip_card_id;
    }

    public int getVip_card_id() {
        return vip_card_id;
    }
}
