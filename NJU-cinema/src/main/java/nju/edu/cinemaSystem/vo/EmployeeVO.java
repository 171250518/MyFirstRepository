package nju.edu.cinemaSystem.vo;

import nju.edu.cinemaSystem.po.Employee;

public class EmployeeVO {
    private Integer id;
    private String username;
    private String password;
    private Integer identity;


    public EmployeeVO(Employee employee){
        this.id = employee.getId();
        this.username = employee.getUsername();
        this.password = employee.getPassword();
        this.identity = employee.getIdentity();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

}
