package noname.VO;

import noname.PO.User;

/**
 * 用户可视化类
/*
* @author wch
* @data 2020/2/29
* */

public class UserVO {
    /**
    * 用户ID
    */
    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public UserVO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UserVO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
