package nju.edu.cinemaSystem.blImpl.promotion;

import nju.edu.cinemaSystem.bl.promotion.ActivityService;
import nju.edu.cinemaSystem.bl.promotion.CouponService;
import nju.edu.cinemaSystem.data.promotion.ActivityMapper;
import nju.edu.cinemaSystem.po.Activity;
import nju.edu.cinemaSystem.po.Coupon;
import nju.edu.cinemaSystem.vo.ActivityForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/20.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    CouponService couponService;

    @Override
    @Transactional
    public ResponseVO publishActivity(ActivityForm activityForm) {
        try {
            ResponseVO vo = couponService.addCoupon(activityForm.getCouponForm());
            Coupon coupon = (Coupon) vo.getContent();
            Activity activity = new Activity();
            activity.setName(activityForm.getName());
            activity.setDescription(activityForm.getName());
            activity.setStartTime(activityForm.getStartTime());
            activity.setEndTime(activityForm.getEndTime());
            activity.setCoupon(coupon);
            activityMapper.insertActivity(activity);
            if(activityForm.getMovieList()!=null&&activityForm.getMovieList().size()!=0){
                activityMapper.insertActivityAndMovie(activity.getId(), activityForm.getMovieList());
            }
            return ResponseVO.buildSuccess(activityMapper.selectById(activity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getActivitiesByMovieId(int movieId){
        try {
            return ResponseVO.buildSuccess(activityMapper.selectActivitiesByMovie(movieId).stream().map(Activity::getVO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO getActivities() {
        try {
            return ResponseVO.buildSuccess(activityMapper.selectActivities().stream().map(Activity::getVO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO deleteActivityById(int activityId){
        try {
            activityMapper.deleteActivityMovieByActivityId(activityId);
            return ResponseVO.buildSuccess(activityMapper.deleteActivityById(activityId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateActivity(ActivityForm activityForm){
        try {
            ResponseVO vo = couponService.addCoupon(activityForm.getCouponForm());
            Coupon coupon = (Coupon) vo.getContent();
            Activity activity = new Activity();
            activity.setName(activityForm.getName());
            activity.setDescription(activityForm.getName());
            activity.setStartTime(activityForm.getStartTime());
            activity.setEndTime(activityForm.getEndTime());
            activity.setCoupon(coupon);
            activityMapper.updateActivity(activity);
            activityMapper.deleteActivityMovieByActivityId(activity.getId());
            if(activityForm.getMovieList()!=null&&activityForm.getMovieList().size()!=0){
                activityMapper.insertActivityAndMovie(activity.getId(), activityForm.getMovieList());
            }
            return ResponseVO.buildSuccess(activityMapper.selectById(activity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getActivitiesByUserId(int userId){
        try {
            return ResponseVO.buildSuccess(activityMapper.selectActivitiesByUser(userId).stream().map(Activity::getVO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO getActivitiesForUser() {
        try {
            List<Activity> activityList=activityMapper.selectActivitiesForUser();
            return ResponseVO.buildSuccess(activityMapper.selectActivitiesForUser().stream().map(Activity::getVO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
