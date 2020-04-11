package nju.edu.cinemaSystem.blImpl.promotion;

import nju.edu.cinemaSystem.bl.promotion.VIPService;
import nju.edu.cinemaSystem.data.promotion.VIPCardMapper;
import nju.edu.cinemaSystem.po.ChargeHist;
import nju.edu.cinemaSystem.po.VIPCardInfo;
import nju.edu.cinemaSystem.vo.VIPCardForm;
import nju.edu.cinemaSystem.po.VIPCard;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.VIPInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nju.edu.cinemaSystem.po.ConsumeHist;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService {
    @Autowired
    VIPCardMapper vipCardMapper;

    @Override
    public ResponseVO addVIPCard(int userId) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
        try {
            int id = vipCardMapper.insertOneCard(vipCard);
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPInfo() {
        VIPCardInfo vipCardInfo = vipCardMapper.selectVIPCardInfo();
        VIPInfoVO vipInfoVO = new VIPInfoVO(vipCardInfo);
        return ResponseVO.buildSuccess(vipInfoVO);
    }

    @Override
    public ResponseVO setVIPInfo(VIPCardInfo vipCardInfo) {
        try {
            vipCardMapper.updateVIPCardInfo(vipCardInfo.getPrice(), vipCardInfo.getTarget(), vipCardInfo.getDiscount());
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {
        VIPCardInfo vipCardInfo = vipCardMapper.selectVIPCardInfo();
        float target = vipCardInfo.getTarget();
        float discount = vipCardInfo.getDiscount();
        int amount = vipCardForm.getAmount();
        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        float balance_befo = vipCard.getBalance();
        float balance_after = vipCard.getBalance() + (int) (amount / target) * discount + amount;
        vipCard.setBalance(balance_after);
        try {
            ChargeHist chargeHist = new ChargeHist(
                    amount, balance_befo, balance_after, new Timestamp(System.currentTimeMillis()), vipCardForm.getVipId()
            );
            vipCardMapper.insertOneChargeHist(chargeHist);
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if (vipCard == null) {
                return ResponseVO.buildFailure("用户卡不存在");
            }
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    public ResponseVO payOrder(int userId, float total) {

        VIPCard s = vipCardMapper.selectCardByUserId(userId);
        if (s.getBalance() >= total) {
            ConsumeHist consumeHist=new ConsumeHist(
                    total, s.getBalance(),s.getBalance()-total,new Timestamp(System.currentTimeMillis()),s.getId()
                    );
            s.setBalance(s.getBalance() - total);
            vipCardMapper.insertOneConsumeHist(consumeHist);
            vipCardMapper.updateCardBalance(s.getId(), s.getBalance());
            return ResponseVO.buildSuccess(s);
        } else {
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCharges(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if (vipCard == null) {
                return ResponseVO.buildFailure("用户卡不存在");
            }
            List<ChargeHist> chargeHists = vipCardMapper.selectChargesByVIPid(vipCard.getId());
            return ResponseVO.buildSuccess(chargeHists);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getChargeDetail(int chargeId) {
        try {
            ChargeHist chargeHist = vipCardMapper.selectCharge(chargeId);
            return ResponseVO.buildSuccess(chargeHist);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public ResponseVO getConsumes(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if (vipCard == null) {
                return ResponseVO.buildFailure("用户卡不存在");
            }
            List<ConsumeHist> consumeHists=vipCardMapper.selectConsumesByVIPid(vipCard.getId());
            return ResponseVO.buildSuccess(consumeHists);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    @Override
    public ResponseVO getConsumeDetail(int consuemId) {
        try {
            ConsumeHist consumeHist = vipCardMapper.selectConsume(consuemId);
            return ResponseVO.buildSuccess(consumeHist);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}