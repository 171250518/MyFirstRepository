package nju.edu.cinemaSystem.po;

import nju.edu.cinemaSystem.vo.TicketRefundPromotionForm;
import nju.edu.cinemaSystem.vo.TicketRefundPromotionVO;

public class TicketRefundPromotion {
    private int id;
    private int Low;
    private int High;
    private double refundRate;
    private int canOrnot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRefundRate() {
        return refundRate;
    }

    public int getCanOrnot() {
        return canOrnot;
    }

    public int getHigh() {
        return High;
    }

    public int getLow() {
        return Low;
    }

    public void setCanOrnot(int canOrnot) {
        this.canOrnot = canOrnot;
    }

    public void setHigh(int high) {
        this.High = high;
    }

    public void setLow(int low) {
        this.Low = low;
    }

    public void setRefundRate(double refundRate) {
        this.refundRate = refundRate;
    }

    public TicketRefundPromotionVO getVO(){
        TicketRefundPromotionVO s=new TicketRefundPromotionVO();
        s.setCanOrnot(canOrnot);
        s.setHigh(High);
        s.setLow(Low);
        s.setNumber(id);
        s.setRefundRate(refundRate);
        return s;
    }
    public TicketRefundPromotion(){

    }
    public TicketRefundPromotion(TicketRefundPromotionForm s){
        this.id=s.getNumber();
this.High=s.getHigh();
this.Low=s.getLow();
this.canOrnot=s.getCanOrnot();
this.refundRate=s.getRefundRate();
    }
}
