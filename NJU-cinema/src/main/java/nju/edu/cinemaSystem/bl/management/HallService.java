package nju.edu.cinemaSystem.bl.management;

import nju.edu.cinemaSystem.vo.HallFrom;
import nju.edu.cinemaSystem.vo.ResponseVO;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
public interface HallService {
    /**
     * 搜索所有影厅
     * @return
     */
    ResponseVO searchAllHall();

    /**
     * 搜索所有影厅
     * @param  newHall
     * @return
     */
    ResponseVO addHall(HallFrom newHall);


    /**
     * 搜索所有影厅
     * @param  newHall
     * @return
     */
    ResponseVO updateHall(HallFrom newHall);

    /**
     * 搜索所有影厅
     * @param  id
     * @return
     */
    ResponseVO deleteHall(int id);
}
