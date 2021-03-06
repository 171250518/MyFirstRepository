package nju.edu.cinemaSystem.controller.statistics;

import nju.edu.cinemaSystem.bl.statistics.StatisticsService;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "statistics/scheduleRate", method = RequestMethod.GET)
    public ResponseVO getScheduleRateByDate(@RequestParam(required = false) Date date){
        //此处date为非必填参数，若不填则默认为当天
        return statisticsService.getScheduleRateByDate(date);
    }

    @RequestMapping(value = "statistics/boxOffice/total", method = RequestMethod.GET)
    public ResponseVO getTotalBoxOffice(){
        return statisticsService.getTotalBoxOffice();
    }

    @RequestMapping(value = "statistics/audience/price", method = RequestMethod.GET)
    public ResponseVO getAudiencePrice(){
        return statisticsService.getAudiencePriceSevenDays();
    }

    @RequestMapping(value = "statistics/PlacingRate", method = RequestMethod.GET)
    public ResponseVO getMoviePlacingRateByDate(@RequestParam String startDate,@RequestParam String endDate){
        return statisticsService.getMoviePlacingRateByDate(startDate,endDate);
    }

    @RequestMapping(value = "statistics/popular/movie", method = RequestMethod.GET)
    public ResponseVO getPopularMovies(@RequestParam int days, @RequestParam int movieNum){
        return statisticsService.getPopularMovies(days, movieNum);
    }










}
