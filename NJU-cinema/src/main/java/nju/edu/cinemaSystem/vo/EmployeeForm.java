package nju.edu.cinemaSystem.vo;

public class EmployeeForm {

    /**
     * 用户id
     */
    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;

    /**
     *用户身份
     */
    private int identity;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentity(){
        return identity;
    }

    public void setIdentity(int identity){
        this.identity=identity;
    }

}
