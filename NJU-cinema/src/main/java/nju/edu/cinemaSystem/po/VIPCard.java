package nju.edu.cinemaSystem.po;


import java.sql.Timestamp;

/**
 * Created by liying on 2019/4/14.
 */

public class VIPCard {
    /**
     * 用户id
     */
    private int userId;

    /**
     * 会员卡id
     */
    private int id;

    /**
     * 会员卡余额
     */
    private float balance;

    /**
     * 办卡日期
     */
    private Timestamp joinDate;


    public VIPCard() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

}
