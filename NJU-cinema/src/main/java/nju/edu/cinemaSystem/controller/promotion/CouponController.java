package nju.edu.cinemaSystem.controller.promotion;

import nju.edu.cinemaSystem.bl.promotion.CouponService;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("{userId}/get")
    public ResponseVO getCoupons(@PathVariable int userId){
        return couponService.getCouponsByUser(userId);
    }

    @GetMapping("/{userId}/{CouponId}/issue")
    public ResponseVO issueCoupon(@PathVariable int userId,@PathVariable int CouponId){return  couponService.issueCoupon(CouponId,userId);}

    @GetMapping("/{userId}/{CouponId}/delete")
    public ResponseVO deleteCouponByuserIdandCouponId(@PathVariable int CouponId,@PathVariable int userId){
        return couponService.deleteCouponByuserIdandCouponId(CouponId,userId);
    }

    @GetMapping("{userId}/{amount}/get")
    public ResponseVO getCoupons(@PathVariable int userId,@PathVariable float amount){
        return couponService.getCouponByUserAndAmount(userId,amount);
    }
}
