package nju.edu.cinemaSystem.controller.promotion;

import nju.edu.cinemaSystem.bl.promotion.VIPService;
import nju.edu.cinemaSystem.po.VIPCardInfo;
import nju.edu.cinemaSystem.vo.VIPCardForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/14.
 */
@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    @Autowired
    VIPService vipService;

    @PostMapping("/add")
    public ResponseVO addVIP(@RequestParam int userId){
        return vipService.addVIPCard(userId);
    }
    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId){
        return vipService.getCardByUserId(userId);
    }

    @GetMapping("/getVIPInfo")
    public ResponseVO getVIPInfo(){
        return vipService.getVIPInfo();
    }

    @PostMapping("/setVIPInfo")
    public ResponseVO setVIPInfo(@RequestBody VIPCardInfo vipCardInfo){
        return vipService.setVIPInfo(vipCardInfo);
    }

    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm){
        return vipService.charge(vipCardForm);
    }


    @GetMapping("/charges/get")
    public ResponseVO getCharges(@RequestParam int userId){
        return vipService.getCharges(userId);
    }

    @GetMapping("/consumes/get")
    public ResponseVO getConsumes(@RequestParam int userId){
        return vipService.getConsumes(userId);
    }

    @GetMapping("/charge/detail")
    public ResponseVO getChargeDetail(@RequestParam int chargeId){
        return vipService.getChargeDetail(chargeId);
    }

    @GetMapping("/consume/detail")
    public ResponseVO getConsumeDetail(@RequestParam int consumeId){
        return vipService.getConsumeDetail(consumeId);
    }

    @GetMapping("/{userId}/{total}/pay")
    public ResponseVO payOder(@PathVariable int userId,@PathVariable float total){
        return  vipService.payOrder(userId,total);
    }

}
