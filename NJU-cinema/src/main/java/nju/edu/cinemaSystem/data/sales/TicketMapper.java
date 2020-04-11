package nju.edu.cinemaSystem.data.sales;

import nju.edu.cinemaSystem.po.Ticket;
import nju.edu.cinemaSystem.po.TicketRefundPromotion;
import nju.edu.cinemaSystem.po.TicketWithOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface TicketMapper {

    int insertTicket(Ticket ticket);

    int insertTickets(List<Ticket> tickets);

    void deleteTicket(int ticketId);

    void updateTicketState(@Param("ticketId") int ticketId, @Param("state") int state);

    List<Ticket> selectTicketsBySchedule(int scheduleId);

    Ticket selectTicketByScheduleIdAndSeat(@Param("scheduleId") int scheduleId, @Param("column") int columnIndex, @Param("row") int rowIndex);

    Ticket selectTicketById(int id);

    List<Ticket> selectTicketByUser(int userId);

    List<TicketRefundPromotion> selectAllRefundPromotion();

    void updateRefundPromotion(TicketRefundPromotion refundPromotion);

    void insertRefundPromotion(TicketRefundPromotion refundPromotion);

    void deleteRefundPromotion(int id);

    @Scheduled(cron = "0/1 * * * * ?")
    void cleanExpiredTicket();

    List<TicketRefundPromotion> selectRefundPromotionByAmount(float amount);
    List<TicketWithOrder> selectOrdersByUserId(@Param("userId") int userId);
    List<TicketWithOrder> selectOrderByUserIdAndTime(@Param("userId") int userId, @Param("orderTime") Timestamp orderTime);
    void deleteTicketByUserAndTime(@Param("userId") int userId, @Param("time") Timestamp time);
}

