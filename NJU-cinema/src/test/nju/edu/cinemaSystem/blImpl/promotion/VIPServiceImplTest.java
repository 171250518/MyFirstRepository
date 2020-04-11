package nju.edu.cinemaSystem.blImpl.promotion;

import nju.edu.cinemaSystem.data.promotion.VIPCardMapper;
import nju.edu.cinemaSystem.po.VIPCardInfo;
import nju.edu.cinemaSystem.vo.ResponseVO;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class VIPServiceImplTest {

    @Autowired
    private VIPCardMapper vipCardMapper;

    @Test
    public void setVIPInfo() {
        VIPCardInfo vipCardInfo=new VIPCardInfo(25,100,10);
        vipCardMapper.updateVIPCardInfo(vipCardInfo.getPrice(), vipCardInfo.getTarget(), vipCardInfo.getDiscount());
        VIPCardInfo res=vipCardMapper.selectVIPCardInfo();
        assertEquals("price:25.00 target:100.00 discount:10.00",res.toString());
    }

    @Test
    public void getCharges() {
    }

    @Test
    public void getChargeDetail() {
    }

    @Test
    public void getConsumes() {
    }

    @Test
    public void getConsumeDetail() {
    }
}