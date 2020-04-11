package nju.edu.cinemaSystem.bl.promotion;

import nju.edu.cinemaSystem.po.VIPCardInfo;
import nju.edu.cinemaSystem.vo.VIPCardForm;
import nju.edu.cinemaSystem.vo.ResponseVO;



/**
 * Created by liying on 2019/4/14.
 */

public interface VIPService {

    ResponseVO addVIPCard(int userId);

    ResponseVO getCardById(int id);

    ResponseVO getVIPInfo();

    ResponseVO setVIPInfo(VIPCardInfo vipCardInfo);

    ResponseVO charge(VIPCardForm vipCardForm);

    ResponseVO getCardByUserId(int userId);

    ResponseVO payOrder(int userId,float total);

    ResponseVO getCharges(int userId);

    ResponseVO getChargeDetail(int chargeId);

    ResponseVO getConsumes(int userId);

    ResponseVO getConsumeDetail(int consumeId);

}
