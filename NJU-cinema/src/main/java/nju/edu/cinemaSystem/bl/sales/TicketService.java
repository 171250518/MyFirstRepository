package nju.edu.cinemaSystem.bl.sales;

import nju.edu.cinemaSystem.po.TicketRefundPromotion;
import nju.edu.cinemaSystem.po.Ticketcpl;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.TicketForm;
import nju.edu.cinemaSystem.vo.TicketRefundPromotionForm;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
public interface TicketService {
    /**
     * TODO:锁座【增加票但状态为未付款】
     *
     * @param ticketForm
     * @return
     */
    ResponseVO addTicket(TicketForm ticketForm);

    /**
     * TODO:完成购票【不使用会员卡】流程包括校验优惠券和根据优惠活动赠送优惠券
     *
     * @param s
     * @return
     */
    ResponseVO completeTicket(List<Ticketcpl> s);


    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    ResponseVO getBySchedule(int scheduleId);

    /**
     * TODO:获得用户买过的票
     *
     * @param userId
     * @return
     */
    ResponseVO getTicketByUser(int userId);

    /**
     * TODO:完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @param s
     * @return
     */
    ResponseVO completeByVIPCard(List<Ticketcpl> s);
    /**
     * TODO:取消锁座（只有状态是"锁定中"的可以取消）
     *
     * @param id
     * @return
     */
    ResponseVO cancelTicket(List<Integer> id);


    /**
     *
     *
     * @param
     * @return
     */
    ResponseVO getRefundPromotion();

    /**
     *
     *
     * @param  s
     * @return
     */
    ResponseVO updateRefundPromotion(TicketRefundPromotionForm s);

    /**
     *
     *
     * @param  id
     * @return
     */
    ResponseVO deleteRefundPromotion(int id);
    /**
     *
     *
     * @param  s
     * @return
     */
    ResponseVO insertRefundPromotion(TicketRefundPromotionForm s);
    /**
     *
     *
     * @param  amount
     * @return
     */
    ResponseVO getRefundPromotionByAmount(float amount);

    ResponseVO selectOrdersByUserId(int userId);

    ResponseVO getOrderDetailByOrderTimeAndUserId(Timestamp orderTime, int userId);

    void refund(int userId, int amount);

    ResponseVO deleteTicketByUserAndTime(int userId, Timestamp time);

}
