package nju.edu.cinemaSystem.bl.promotion;

import nju.edu.cinemaSystem.vo.ActivityForm;
import nju.edu.cinemaSystem.vo.ResponseVO;

/**
 * Created by liying on 2019/4/20.
 */
public interface ActivityService {
    
    ResponseVO publishActivity(ActivityForm activityForm);

    ResponseVO getActivities();

    ResponseVO getActivitiesByMovieId(int movieId);

    ResponseVO deleteActivityById(int activityId);

    ResponseVO updateActivity(ActivityForm activityForm);

    ResponseVO getActivitiesByUserId(int userId);

    ResponseVO getActivitiesForUser();

}
