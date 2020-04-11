package nju.edu.cinemaSystem.blImpl.management.schedule;

import nju.edu.cinemaSystem.po.Movie;
import nju.edu.cinemaSystem.vo.MovieVO;

/**
 * @author fjj
 * @date 2019/4/28 12:29 AM
 */
public interface MovieServiceForBl {
    /**
     * 根据id查找电影
     * @param id
     * @return
     */
    Movie getMovieById(int id);
}
