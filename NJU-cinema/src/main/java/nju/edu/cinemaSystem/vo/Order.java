package nju.edu.cinemaSystem.vo;

import nju.edu.cinemaSystem.po.TicketWithOrder;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private List<TicketWithOrder> ticketList;
    private Timestamp order_time;
    private double fare;

    public List<TicketWithOrder> getTicketList(){
        return ticketList;
    }

    public void setTicketList(List<TicketWithOrder> ticketList){
        this.ticketList=ticketList;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

}
