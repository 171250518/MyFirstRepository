package nju.edu.cinemaSystem.blImpl.statistics;

import nju.edu.cinemaSystem.bl.statistics.StatisticsService;
import nju.edu.cinemaSystem.data.management.HallMapper;
import nju.edu.cinemaSystem.data.statistics.StatisticsMapper;
import nju.edu.cinemaSystem.po.*;
import nju.edu.cinemaSystem.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    private HallMapper hallMapper;

    @Override
    public ResponseVO getScheduleRateByDate(Date date) {
        try{
            Date requireDate = date;
            if(requireDate == null){
                requireDate = new Date();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));

            Date nextDate = getNumDayAfterDate(requireDate, 1);
            return ResponseVO.buildSuccess(movieScheduleTimeList2MovieScheduleTimeVOList(statisticsMapper.selectMovieScheduleTimes(requireDate, nextDate)));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTotalBoxOffice() {
        try {
            return ResponseVO.buildSuccess(movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(statisticsMapper.selectMovieTotalBoxOffice()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAudiencePriceSevenDays() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date startDate = getNumDayAfterDate(today, -6);
            List<AudiencePriceVO> audiencePriceVOList = new ArrayList<>();
            for(int i = 0; i < 7; i++){
                AudiencePriceVO audiencePriceVO = new AudiencePriceVO();
                Date date = getNumDayAfterDate(startDate, i);
                audiencePriceVO.setDate(date);
                List<AudiencePrice> audiencePriceList = statisticsMapper.selectAudiencePrice(date, getNumDayAfterDate(date, 1));
                double totalPrice = audiencePriceList.stream().mapToDouble(item -> item.getTotalPrice()).sum();
                audiencePriceVO.setPrice(Double.parseDouble(String.format("%.2f", audiencePriceList.size() == 0 ? 0 : totalPrice / audiencePriceList.size())));
                audiencePriceVOList.add(audiencePriceVO);
            }
            return ResponseVO.buildSuccess(audiencePriceVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getMoviePlacingRateByDate(String startDate,String endDate) {
        //获取所有电影某天的上座率
        //上座率参考公式：假设某影城设有n 个电影厅、m 个座位数，相对上座率=观众人次÷放映场次÷m÷n×100%
        //此方法中运用到的相应的操作数据库的接口和实现请自行定义和实现，相应的结果需要自己定义一个VO类返回给前端
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date=sdf.parse(startDate);
            Date nextDate=sdf.parse(endDate);
//            List<Hall> halls=hallMapper.selectAllHall();
//            int temp=0;
//            for(Hall hall:halls){
//                temp+=hall.getColumn()*hall.getRow();
//            }
//            double avgSize=(double)temp/(double)halls.size();
            //movieId startTime(=date) state(=1)
            double avgSize=72.0;
            List<MoviePlacingRate> moviePlacingRates=statisticsMapper.getPlacingRate(date,nextDate);
            List<MoviePlacingRateVO> moviePlacingRateVOS=moviePlacingRateList2MoviePlacingRateVOList(moviePlacingRates,avgSize);
            return ResponseVO.buildSuccess(moviePlacingRateVOS);
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getPopularMovies(int days, int movieNum) {
        //要求见接口说明
        try {
            Date dateNow=new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateNow = simpleDateFormat.parse(simpleDateFormat.format(dateNow));
            Date requireDate=getNumDayAfterDate(dateNow,0-days);
            List<MovieTotalBoxOffice> list=statisticsMapper.selectPopularMovie(requireDate, movieNum);
            List<MovieTotalBoxOfficeVO> list2=movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(list);

            return ResponseVO.buildSuccess(list2);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    /**
     * 获得num天后的日期
     * @param oldDate
     * @param num
     * @return
     */
    Date getNumDayAfterDate(Date oldDate, int num){
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }



    private List<MovieScheduleTimeVO> movieScheduleTimeList2MovieScheduleTimeVOList(List<MovieScheduleTime> movieScheduleTimeList){
        List<MovieScheduleTimeVO> movieScheduleTimeVOList = new ArrayList<>();
        for(MovieScheduleTime movieScheduleTime : movieScheduleTimeList){
            movieScheduleTimeVOList.add(new MovieScheduleTimeVO(movieScheduleTime));
        }
        return movieScheduleTimeVOList;
    }


    private List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(List<MovieTotalBoxOffice> movieTotalBoxOfficeList){
        List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeVOList = new ArrayList<>();
        for(MovieTotalBoxOffice movieTotalBoxOffice : movieTotalBoxOfficeList){
            movieTotalBoxOfficeVOList.add(new MovieTotalBoxOfficeVO(movieTotalBoxOffice));
        }
        return movieTotalBoxOfficeVOList;
    }

    private List<MoviePlacingRateVO> moviePlacingRateList2MoviePlacingRateVOList(List<MoviePlacingRate> moviePlacingRates,double avgSize){
        ArrayList<MoviePlacingRateVO> moviePlacingRateVOS=new ArrayList<>();
        for(MoviePlacingRate moviePlacingRate:moviePlacingRates){
            moviePlacingRateVOS.add(new MoviePlacingRateVO(moviePlacingRate,avgSize));
        }
        return moviePlacingRateVOS;
    }
}
