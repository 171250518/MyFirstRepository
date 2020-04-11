package nju.edu.cinemaSystem.bl.promotion;

import nju.edu.cinemaSystem.vo.CouponForm;
import nju.edu.cinemaSystem.vo.ResponseVO;

/**
 * Created by liying on 2019/4/17.
 */
public interface CouponService {

    ResponseVO getCouponsByUser(int userId);

    ResponseVO addCoupon(CouponForm couponForm);

    ResponseVO issueCoupon(int couponId, int userId);

    ResponseVO deleteCouponByuserIdandCouponId(int couponId, int userId);

    ResponseVO getCouponByUserAndAmount(int userId, float amount);


}
