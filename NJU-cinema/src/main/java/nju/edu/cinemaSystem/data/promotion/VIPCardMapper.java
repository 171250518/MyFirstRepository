package nju.edu.cinemaSystem.data.promotion;

import nju.edu.cinemaSystem.po.ChargeHist;
import nju.edu.cinemaSystem.po.VIPCard;
import nju.edu.cinemaSystem.po.VIPCardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import nju.edu.cinemaSystem.po.ConsumeHist;
import java.util.List;

/**
 * Created by liying on 2019/4/14.
 */
@Mapper
public interface VIPCardMapper {

    int insertOneCard(VIPCard vipCard);

    VIPCard selectCardById(int id);

    void updateCardBalance(@Param("id") int id,@Param("balance") double balance);

    VIPCard selectCardByUserId(int userId);

    VIPCardInfo selectVIPCardInfo();

    void updateVIPCardInfo(@Param("price") float price,@Param("target") float target,@Param("discount") float discount);

    void insertOneChargeHist(ChargeHist chargeHist);

    List<ChargeHist> selectChargesByVIPid(@Param("vip_card_id") int vip_card_id);

    ChargeHist selectCharge(@Param("id") int id);

    void insertOneConsumeHist(ConsumeHist consumeHist);

    List<ConsumeHist> selectConsumesByVIPid(@Param("vip_card_id") int vip_card_id);

    ConsumeHist selectConsume(@Param("id") int id);
}
