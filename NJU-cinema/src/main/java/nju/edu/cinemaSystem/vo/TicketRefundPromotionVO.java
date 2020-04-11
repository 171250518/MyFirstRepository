package nju.edu.cinemaSystem.vo;

public class TicketRefundPromotionVO {
    private int number;
    private int low;
    private int high;
    private double refundRate;
    private int canOrnot;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        number = number;
    }

    public double getRefundRate() {
        return refundRate;
    }

    public int getCanOrnot() {
        return canOrnot;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public void setCanOrnot(int canOrnot) {
        this.canOrnot = canOrnot;
    }

    public void setHigh(int high) {
        high = high;
    }

    public void setLow(int low) {
        low = low;
    }

    public void setRefundRate(double refundRate) {
        this.refundRate = refundRate;
    }
    public TicketRefundPromotionVO(){


    }}
