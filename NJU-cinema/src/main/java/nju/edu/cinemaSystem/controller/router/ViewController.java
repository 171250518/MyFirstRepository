package nju.edu.cinemaSystem.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

/**
 * @author deng
 * @date 2019/03/11
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/admin/movie/manage")
    public String getAdminMovieManage() {
        return "adminMovieManage";
    }

    @RequestMapping(value = "/admin/session/manage")
    public String getAdminSessionManage() {
        return "adminScheduleManage";
    }

    @RequestMapping(value = "/admin/employee/manage")
    public String getAdminEmployeeManage() {
        return "adminEmployeeManage";
    }

    @RequestMapping(value = "/admin/vipCard/manage")
    public String getAdminVipCardManage() {
        return "adminVIPCardManage";
    }

    @RequestMapping(value = "/admin/cinema/manage")
    public String getAdminCinemaManage() {
        return "adminCinemaManage";
    }

    @RequestMapping(value = "/admin/promotion/manage")
    public String getAdminPromotionManage() {
        return "adminPromotionManage";
    }

    @RequestMapping(value = "/admin/cinema/statistic")
    public String getAdminCinemaStatistic() {
        return "adminCinemaStatistic";
    }

    @RequestMapping(value = "/admin/movieDetail")
    public String getAdminMovieDetail(@RequestParam int id) { return "adminMovieDetail"; }

    @RequestMapping(value = "/user/buy")
    public String getUserBuy() {
        return "userBuy";
    }

    @RequestMapping(value = "/user/movieDetail")
    public String getUserMovieDetail(@RequestParam int id) {
        return "userMovieDetail";
    }

    @RequestMapping(value = "/user/movieDetail/buy")
    public String getUserMovieBuy(@RequestParam int id) {
        return "userMovieBuy";
    }

    @RequestMapping(value = "/user/movie")
    public String getUserMovie() {
        return "userMovie";
    }

    @RequestMapping(value = "/user/member")
    public String getUserMember() {
        return "userMember";
    }

    @RequestMapping(value = "/user/order")
    public String getUserOrder() {
        return "userOrder";
    }

    @RequestMapping(value = "/user/orderDetail")
    public String getUserOrderDetail(@RequestParam String orderTime) {
        return "userOrderDetail";
    }

    @RequestMapping(value = "/user/profile")
    public String getUserProfile() {
        return "userProfile";
    }

    @RequestMapping(value = "/user/promotion")
    public String getUserPromotion() {
        return "userPromotion";
    }
}
