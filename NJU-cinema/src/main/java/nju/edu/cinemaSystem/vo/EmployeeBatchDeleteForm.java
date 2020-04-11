package nju.edu.cinemaSystem.vo;

import java.util.List;

public class EmployeeBatchDeleteForm {
    /**
     * 要删除的职员的id列表
     */
    private List<Integer> employeeIdList;

    public List<Integer> getEmployeeIdList() {
        return employeeIdList;
    }

    public void setEmployeeIdList(List<Integer> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }
}
