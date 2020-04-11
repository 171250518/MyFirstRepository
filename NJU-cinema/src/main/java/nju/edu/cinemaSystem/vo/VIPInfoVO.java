package nju.edu.cinemaSystem.vo;


import nju.edu.cinemaSystem.po.VIPCardInfo;

/**
 * Created by liying on 2019/4/15.
 */
public class VIPInfoVO {

    private float price;

    private float target;

    private float discount;

    public VIPInfoVO(VIPCardInfo vipCardInfo){
        this.price=vipCardInfo.getPrice();
        this.target=vipCardInfo.getTarget();
        this.discount=vipCardInfo.getDiscount();
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public float getTarget() {
        return target;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getDiscount() {
        return discount;
    }
}
