package nju.edu.cinemaSystem.data.statistics;

import nju.edu.cinemaSystem.po.AudiencePrice;
import nju.edu.cinemaSystem.po.MoviePlacingRate;
import nju.edu.cinemaSystem.po.MovieScheduleTime;
import nju.edu.cinemaSystem.po.MovieTotalBoxOffice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:43 PM
 */
@Mapper
public interface StatisticsMapper {
    /**
     * 查询date日期每部电影的排片次数
     * @param date
     * @return
     */
    List<MovieScheduleTime> selectMovieScheduleTimes(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询所有电影的总票房（包括已经下架的，降序排列）
     * @return
     */
    List<MovieTotalBoxOffice> selectMovieTotalBoxOffice();

    List<MovieTotalBoxOffice> selectPopularMovie(@Param("requireDate") Date requireDate, @Param("movieNum") int movieNum);

    /**
     * 查询某天每个客户的购票金额
     * @param date
     * @param nextDate
     * @return
     */
    List<AudiencePrice> selectAudiencePrice(@Param("date") Date date, @Param("nextDate") Date nextDate);

    List<MoviePlacingRate> getPlacingRate(@Param("date") Date date, @Param("nextDate") Date nextDate);
}
