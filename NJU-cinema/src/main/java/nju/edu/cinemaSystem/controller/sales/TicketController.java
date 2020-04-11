package nju.edu.cinemaSystem.controller.sales;

import nju.edu.cinemaSystem.bl.sales.TicketService;
import nju.edu.cinemaSystem.po.Ticketcpl;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.TicketForm;
import nju.edu.cinemaSystem.vo.TicketRefundPromotionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestBody List<Ticketcpl> s){
        return ticketService.completeByVIPCard(s);
    }


    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm){
        return ticketService.addTicket(ticketForm);
    }
    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestBody List<Ticketcpl> s){
        return ticketService.completeTicket(s);
    }

    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId){
        return ticketService.getTicketByUser(userId);
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId){
        return ticketService.getBySchedule(scheduleId);
    }
    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestParam List<Integer> ticketId){
        return ticketService.cancelTicket(ticketId);
    }

    @RequestMapping(value = "/refund/promotion/all", method = RequestMethod.GET)
    public ResponseVO getRefundPromotion(){
        return ticketService.getRefundPromotion();
    }

    @PostMapping("/refund/promotion/update")
    public ResponseVO updateRefundPromotion(@RequestBody TicketRefundPromotionForm newRefundPro){
        return ticketService.updateRefundPromotion(newRefundPro);
    }
    @PostMapping("/refund/promotion/add")
    public ResponseVO insertRefundPromotion(@RequestBody TicketRefundPromotionForm newRefundPro){
        return ticketService.insertRefundPromotion(newRefundPro);
    }

    @GetMapping("/refund/promotion/delete/{Id}")
    public ResponseVO deleteRefundPromotion(@PathVariable int Id){
        return ticketService.deleteRefundPromotion(Id);
    }

    @GetMapping("/refund/promotion/search/{amount}")
    public ResponseVO searchRefundPromotionByAmount(@PathVariable float amount){
        return ticketService.getRefundPromotionByAmount(amount);
    }

    @GetMapping("/order/get/{userId}")
    public ResponseVO selectOrdersByUserId(@PathVariable int userId){
        return ticketService.selectOrdersByUserId(userId);
    }

    @GetMapping("/order/detail/{orderTime}/{userId}")
    public ResponseVO getOrderDetailByOrderTimeAndUserId(@PathVariable String orderTime, @PathVariable int userId){

        return ticketService.getOrderDetailByOrderTimeAndUserId(formatDate(orderTime), userId);
    }

    @GetMapping("/refund/{userId}/{amount}")
    public void refund(@PathVariable int userId, @PathVariable int amount){
        ticketService.refund(userId, amount);
    }

    private Timestamp formatDate(String inputDate){
        try{
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            Date date = inputFormat.parse(inputDate);
            return new Timestamp(date.getTime());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/delete/ticket/{userId}/{time}")
    public ResponseVO deleteTicketByTime(@PathVariable int userId, @PathVariable String time){
        return ResponseVO.buildSuccess(ticketService.deleteTicketByUserAndTime(userId, formatDate(time)));
    }

}
