package nju.edu.cinemaSystem.po;

import java.sql.Timestamp;

public class TicketWithOrder {

    private String movie_name;
    private String poster_url;
    private String hall_name;
    private int column_index;
    private int row_index;
    private Timestamp start_time;
    private Timestamp end_time;
    private double fare;
    private Timestamp order_time;

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getposter_url(){
        return poster_url;
    }

    public void setposter_url(String poster_url){
        this.poster_url = poster_url;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public int getColumn_index() {
        return column_index;
    }

    public void setColumn_index(int column_index) {
        this.column_index = column_index;
    }

    public int getRow_index() {
        return row_index;
    }

    public void setRow_index(int row_index) {
        this.row_index = row_index;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }
}
