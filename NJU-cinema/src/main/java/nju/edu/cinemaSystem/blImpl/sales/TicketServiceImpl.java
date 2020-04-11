package nju.edu.cinemaSystem.blImpl.sales;

import nju.edu.cinemaSystem.bl.promotion.CouponService;
import nju.edu.cinemaSystem.bl.sales.TicketService;
import nju.edu.cinemaSystem.blImpl.management.hall.HallServiceForBl;
import nju.edu.cinemaSystem.blImpl.management.schedule.ScheduleServiceForBl;
import nju.edu.cinemaSystem.data.promotion.CouponMapper;
import nju.edu.cinemaSystem.data.promotion.VIPCardMapper;
import nju.edu.cinemaSystem.data.sales.TicketMapper;
import nju.edu.cinemaSystem.po.*;
import nju.edu.cinemaSystem.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    VIPCardMapper vipCardMapper;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        List<Integer> d = new ArrayList<Integer>();
        for (SeatForm seats : ticketForm.getSeats()) {
            Ticket newTicket = new Ticket();
            newTicket.setUserId(ticketForm.getUserId());
            newTicket.setColumnIndex(seats.getColumnIndex());
            newTicket.setRowIndex(seats.getRowIndex());
            newTicket.setScheduleId(ticketForm.getScheduleId());
            newTicket.setState(0);
            Timestamp date = new Timestamp(System.currentTimeMillis());
            newTicket.setTime(date);
            ticketMapper.insertTicket(newTicket);
            newTicket.setId(ticketMapper.selectTicketByScheduleIdAndSeat(newTicket.getScheduleId(), newTicket.getColumnIndex(), newTicket.getRowIndex()).getId());
            d.add(newTicket.getId());
        }
        return ResponseVO.buildSuccess(d);
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(List<Ticketcpl> s) {
        for (Ticketcpl a : s) {
            ticketMapper.updateTicketState(a.getId(), 1);
        }
        if (s.get(0).getCouponId() != 0) {
            couponMapper.deleteCouponUser(s.get(0).getCouponId(), ticketMapper.selectTicketById(s.get(0).getId()).getUserId());
        }
        return null;
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule = scheduleService.getScheduleItemById(scheduleId);
            Hall hall = hallService.getHallById(schedule.getHallId());
            int[][] seats = new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO = new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketByUser(userId);
            List<TicketWithScheduleVO> list = new ArrayList<TicketWithScheduleVO>();
            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                TicketWithScheduleVO vo = ticket.getWithScheduleVO();
                ScheduleItem item = scheduleService.getScheduleItemById(ticket.getScheduleId());
                vo.setSchedule(item);
                list.add(vo);
            }
            return ResponseVO.buildSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    public ResponseVO getRefundPromotionByAmount(float amount) {
        try {
            List<TicketRefundPromotion> s= ticketMapper.selectRefundPromotionByAmount(amount);
            return ResponseVO.buildSuccess(s);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Ticketcpl> s) {
        for (Ticketcpl a : s) {
            ticketMapper.updateTicketState(a.getId(), 1);
        }
        if (s.get(0).getCouponId() != 0) {
            System.out.println(s.get(0).getCouponId());
            System.out.println(ticketMapper.selectTicketById(s.get(0).getId()).getUserId());
            couponMapper.deleteCouponUser(s.get(0).getCouponId(), ticketMapper.selectTicketById(s.get(0).getId()).getUserId());
        }
        return null;
    }


    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        for (int a : id) {
            ticketMapper.deleteTicket(a);
        }
        return null;
    }


    @Override
    public ResponseVO getRefundPromotion() {
        return ResponseVO.buildSuccess(ticketMapper.selectAllRefundPromotion());
    }

    @Override
    public ResponseVO updateRefundPromotion(TicketRefundPromotionForm s) {
        TicketRefundPromotion n = new TicketRefundPromotion(s);
        try{
        ticketMapper.updateRefundPromotion(n);}
        catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
        return ResponseVO.buildSuccess();
    }


    @Override
    public ResponseVO insertRefundPromotion(TicketRefundPromotionForm s) {
        TicketRefundPromotion n = new TicketRefundPromotion(s);
        ticketMapper.insertRefundPromotion(n);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO deleteRefundPromotion(int id) {
        try {
            ticketMapper.deleteRefundPromotion(id);
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO selectOrdersByUserId(int userId){
        try {
            List<TicketWithOrder> list = ticketMapper.selectOrdersByUserId(userId);
            List<Order> orders = new ArrayList<Order>();
            Order order = new Order();
            for(int i = 0; i < list.size(); i++){
                TicketWithOrder ticket = list.get(i);
                //同一订单的不同票
                if(order.getOrder_time()!=null&&order.getOrder_time().getTime() - ticket.getOrder_time().getTime() == 0){
                    order.getTicketList().add(ticket);
                    order.setFare(order.getFare()+ticket.getFare());
                }else {//新订单
                    if(order.getOrder_time()!=null){
                        orders.add(order);
                        order = new Order();
                    }
                    order.setFare(ticket.getFare());
                    order.setOrder_time(ticket.getOrder_time());
                    order.setTicketList(new ArrayList<TicketWithOrder>());
                    order.getTicketList().add(ticket);
                }
            }
            orders.add(order);
            return ResponseVO.buildSuccess(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getOrderDetailByOrderTimeAndUserId(Timestamp orderTime, int userId){
        try {
            List<TicketWithOrder> list = ticketMapper.selectOrderByUserIdAndTime(userId, orderTime);
            Order order = new Order();
            order.setTicketList(list);
            order.setOrder_time(list.get(0).getOrder_time());
            order.setFare(list.get(0).getFare()*list.size());
            return ResponseVO.buildSuccess(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public void refund(int userId, int amount){
        VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
        vipCardMapper.updateCardBalance(vipCard.getId(),amount+vipCard.getBalance());
    }

    @Override
    public ResponseVO deleteTicketByUserAndTime(int userId, Timestamp time){
        try {
            ticketMapper.deleteTicketByUserAndTime(userId, time);;
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }
        return ResponseVO.buildSuccess();
    }

}
