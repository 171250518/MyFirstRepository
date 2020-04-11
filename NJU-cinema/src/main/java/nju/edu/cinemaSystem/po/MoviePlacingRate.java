package nju.edu.cinemaSystem.po;

import java.util.Date;

public class MoviePlacingRate {
    //假设某影城设有n 个电影厅、m 个座位数，相对上座率=观众人次÷放映场次÷m÷n×100%
    private String movieName;
    private int numOfAudience;
    private int numOfSchedule;

    public int getNumOfAudience() {
        return numOfAudience;
    }

    public int getNumOfSchedule() {
        return numOfSchedule;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setNumOfAudience(int numOfAudience) {
        this.numOfAudience = numOfAudience;
    }

    public void setNumOfSchedule(int numOfSchedule) {
        this.numOfSchedule = numOfSchedule;
    }
}
