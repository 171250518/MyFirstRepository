package nju.edu.cinemaSystem.blImpl.management.employee;

import nju.edu.cinemaSystem.bl.management.EmployeeService;
import nju.edu.cinemaSystem.data.management.EmployeeMapper;
import nju.edu.cinemaSystem.po.Employee;
import nju.edu.cinemaSystem.vo.EmployeeVO;
import nju.edu.cinemaSystem.vo.EmployeeForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.EmployeeBatchDeleteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String ID_LIST_NULL_ERROR_MESSAGE = "id列表不可为空";

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    //增加职员
    public ResponseVO addEmployee(EmployeeForm addEmployeeForm) {
        try {
            employeeMapper.insertOneEmployee(addEmployeeForm.getUsername(), addEmployeeForm.getPassword(),addEmployeeForm.getIdentity());
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    @Override
    public ResponseVO deleteBatchOfEmployee(EmployeeBatchDeleteForm employeeBatchDeleteForm) {
        try{
            List<Integer> employeeIdList = employeeBatchDeleteForm.getEmployeeIdList();
            if(employeeIdList.size() == 0){
                return ResponseVO.buildFailure(ID_LIST_NULL_ERROR_MESSAGE);
            }
            employeeMapper.deleteEmployeeBatch(employeeIdList);
            return ResponseVO.buildSuccess();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateEmployee(EmployeeForm updateEmployeeForm){
        try {

            employeeMapper.updateEmployee(updateEmployeeForm);
            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getEmployees() {
        try {
            List<Employee> employees=employeeMapper.selectEmployees();
            return ResponseVO.buildSuccess(employeeList2EmployeeVOList(employees));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getEmployeeById(int id) {
        return null;
    }

    private List<EmployeeVO> employeeList2EmployeeVOList(List<Employee> employeeList){
        List<EmployeeVO> employeeVOList = new ArrayList<>();
        for(Employee employee : employeeList){
            employeeVOList.add(new EmployeeVO(employee));
        }
        return employeeVOList;
    }

}
