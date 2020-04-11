package nju.edu.cinemaSystem.bl.management;
import nju.edu.cinemaSystem.vo.EmployeeForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.EmployeeBatchDeleteForm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface EmployeeService {
    /*
    新增职员
    * @param addEmployeeForm
     * @return
     */
    ResponseVO addEmployee(EmployeeForm addEmployeeForm);

    /**
     * 删除职员信息
     *
     * @param employeeBatchDeleteForm
     * @return
     */
    ResponseVO deleteBatchOfEmployee(EmployeeBatchDeleteForm employeeBatchDeleteForm);

    /**
     * 更新职员信息
     * @param updateEmployeeForm
     * @return
     */
    ResponseVO updateEmployee(EmployeeForm updateEmployeeForm);
    /**
     * 搜索全部职员(不包括用户)
     * @return
     */
    ResponseVO getEmployees();

    ResponseVO getEmployeeById(int id);

}