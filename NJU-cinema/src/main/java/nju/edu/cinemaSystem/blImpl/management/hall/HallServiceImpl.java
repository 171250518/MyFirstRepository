package nju.edu.cinemaSystem.blImpl.management.hall;

import nju.edu.cinemaSystem.bl.management.HallService;
import nju.edu.cinemaSystem.data.management.HallMapper;
import nju.edu.cinemaSystem.po.Hall;
import nju.edu.cinemaSystem.vo.HallFrom;
import nju.edu.cinemaSystem.vo.HallVO;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
@Service
public class HallServiceImpl implements HallService, HallServiceForBl {
    @Autowired
    private HallMapper hallMapper;

    @Override
    public ResponseVO searchAllHall() {
        try {
            return ResponseVO.buildSuccess(hallList2HallVOList(hallMapper.selectAllHall()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public Hall getHallById(int id) {
        try {
            return hallMapper.selectHallById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public ResponseVO addHall(HallFrom newHall) {
        Hall hall=new Hall();
        hall.setColumn(newHall.getColumn());
        hall.setName(newHall.getName());
        hall.setRow(newHall.getRow());
        System.out.println(newHall.getName());
      try {
            hallMapper.insertHall(hall);
        }
        catch (Exception e){
            return ResponseVO.buildFailure("添加失败");
        }
        return ResponseVO.buildSuccess();

    }


    @Override
    public ResponseVO updateHall(HallFrom newHall) {
        Hall hall=new Hall();
        hall.setColumn(newHall.getColumn());
        hall.setName(newHall.getName());
        hall.setRow(newHall.getRow());
        hall.setId(newHall.getId());
        try {
            hallMapper.updateHall(hall);
      }
        catch (Exception e){
            return ResponseVO.buildFailure("修改失败");
        }
        return ResponseVO.buildSuccess();

    }

    @Override
    public ResponseVO deleteHall(int id){
        try {
            hallMapper.deleteHall(id);
        }catch (Exception e){
            return ResponseVO.buildFailure("删除失败");
        }

        return ResponseVO.buildSuccess();
    }



    private List<HallVO> hallList2HallVOList(List<Hall> hallList){
        List<HallVO> hallVOList = new ArrayList<>();
        for(Hall hall : hallList){
            hallVOList.add(new HallVO(hall));
        }
        return hallVOList;
    }
}
