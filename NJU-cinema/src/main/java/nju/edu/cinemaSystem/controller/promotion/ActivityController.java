package nju.edu.cinemaSystem.controller.promotion;

import nju.edu.cinemaSystem.bl.promotion.ActivityService;
import nju.edu.cinemaSystem.vo.ActivityForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by liying on 2019/4/20.
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/publish")
    public ResponseVO publishActivity(@RequestBody ActivityForm activityForm){
        return activityService.publishActivity(activityForm);
    }
    @GetMapping("/get")
    public ResponseVO getActivities(){
        return activityService.getActivities();
    }

    @GetMapping("/get/{movieId}")
    public ResponseVO getActivities(@PathVariable int movieId){
        return activityService.getActivitiesByMovieId(movieId);
    }

    @GetMapping("/delete/{activityId}")
    public ResponseVO deleteActivityById(@PathVariable int activityId){
        return activityService.deleteActivityById(activityId);
    }

    @PostMapping("/update")
    public ResponseVO updateActivity(@RequestBody ActivityForm activityForm){
        return activityService.updateActivity(activityForm);
    }

    @GetMapping("/get/user/{userId}")
    public ResponseVO getActivitiesByUserId(@PathVariable int userId){
        return activityService.getActivitiesByUserId(userId);
    }

    @GetMapping("/get/userActivity")
    public ResponseVO getActivitiesForUser(){
        return activityService.getActivitiesForUser();
    }

}
