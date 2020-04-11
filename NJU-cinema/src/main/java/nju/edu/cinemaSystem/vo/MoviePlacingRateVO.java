package nju.edu.cinemaSystem.vo;

import nju.edu.cinemaSystem.po.MoviePlacingRate;

import java.util.Date;
import java.util.List;

public class MoviePlacingRateVO {
    private String movieName;
    private double placingRate;

    public MoviePlacingRateVO(MoviePlacingRate moviePlacingRate,double avgSize){
        this.movieName=moviePlacingRate.getMovieName();
        this.placingRate=(double)moviePlacingRate.getNumOfAudience()/(double)moviePlacingRate.getNumOfSchedule()/avgSize;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setPlacingRate(double placingRate) {
        this.placingRate = placingRate;
    }

    public double getPlacingRate() {
        return placingRate;
    }
}
