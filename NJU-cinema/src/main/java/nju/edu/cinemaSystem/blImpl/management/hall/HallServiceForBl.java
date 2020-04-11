package nju.edu.cinemaSystem.blImpl.management.hall;

import nju.edu.cinemaSystem.po.Hall;
/**
 * @author fjj
 * @date 2019/4/28 12:27 AM
 */
public interface HallServiceForBl {
    /**
     * 搜索影厅
     * @param id
     * @return
     */
    Hall getHallById(int id);
}
