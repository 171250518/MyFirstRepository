package nju.edu.cinemaSystem.controller.management;

import nju.edu.cinemaSystem.bl.management.HallService;
import nju.edu.cinemaSystem.vo.HallFrom;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**影厅管理
 * @author fjj
 * @date 2019/4/12 1:59 PM
 */
@RestController
public class HallController {
    @Autowired
    private HallService hallService;

    @RequestMapping(value = "hall/all", method = RequestMethod.GET)
    public ResponseVO searchAllHall(){
        return hallService.searchAllHall();
    }

    @PostMapping(value = "hall/add")
    public ResponseVO addHall(@RequestBody HallFrom hall){
       return hallService.addHall(hall);
    }

    @PostMapping(value = "hall/update")
    public ResponseVO updateHall(@RequestBody HallFrom hall){
        return hallService.updateHall(hall);
    }

    @GetMapping(value = "hall/delete/{id}")
    public ResponseVO deleteHall(@PathVariable int id){
        return hallService.deleteHall(id);
    }


}
