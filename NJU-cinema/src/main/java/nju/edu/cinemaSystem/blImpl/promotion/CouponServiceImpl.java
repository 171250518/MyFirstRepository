package nju.edu.cinemaSystem.blImpl.promotion;

import nju.edu.cinemaSystem.bl.promotion.CouponService;
import nju.edu.cinemaSystem.data.promotion.CouponMapper;
import nju.edu.cinemaSystem.po.Coupon;
import nju.edu.cinemaSystem.vo.CouponForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public ResponseVO getCouponsByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(couponMapper.selectCouponByUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    public ResponseVO getCouponByUserAndAmount(int userId,float amount) {
        try {
            List<Coupon> couponList=couponMapper.selectCouponByUserAndAmount(userId,amount);
            return ResponseVO.buildSuccess(couponList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addCoupon(CouponForm couponForm) {
        try {
            Coupon coupon=new Coupon();
            coupon.setName(couponForm.getName());
            coupon.setDescription(couponForm.getDescription());
            coupon.setTargetAmount(couponForm.getTargetAmount());
            coupon.setDiscountAmount(couponForm.getDiscountAmount());
            coupon.setStartTime(couponForm.getStartTime());
            coupon.setEndTime(couponForm.getEndTime());
            coupon.setConsumeReq((couponForm.getConsumeReq()));
            couponMapper.insertCoupon(coupon);
            return ResponseVO.buildSuccess(coupon);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO issueCoupon(int couponId, int userId) {
        try {
            couponMapper.insertCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }


    @Override
    public ResponseVO deleteCouponByuserIdandCouponId(int couponId,int userId){
        couponMapper.deleteCouponUser(couponId,userId);
        return null;
    }
}
