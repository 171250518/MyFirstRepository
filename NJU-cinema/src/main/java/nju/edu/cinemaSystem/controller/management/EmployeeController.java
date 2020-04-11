package nju.edu.cinemaSystem.controller.management;

import nju.edu.cinemaSystem.bl.management.EmployeeService;
import nju.edu.cinemaSystem.vo.EmployeeForm;
import nju.edu.cinemaSystem.vo.ResponseVO;
import nju.edu.cinemaSystem.vo.EmployeeBatchDeleteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping (value = "/employee/add", method = RequestMethod.POST)
    public ResponseVO addEmployee(@RequestBody EmployeeForm addEmployeeForm){
        return employeeService.addEmployee(addEmployeeForm);
    }


    @RequestMapping (value = "/employee/update",method = RequestMethod.POST)
    public ResponseVO updateEmployee(@RequestBody EmployeeForm updateEmployeeForm){
        return employeeService.updateEmployee(updateEmployeeForm);
    }

    @RequestMapping(value = "/employee/delete/batch" ,method = RequestMethod.DELETE)
    public ResponseVO deleteBatchOfEmployee(@RequestBody EmployeeBatchDeleteForm employeeBatchDeleteForm){
        return employeeService.deleteBatchOfEmployee(employeeBatchDeleteForm);
    }

    @RequestMapping(value="/employee/get",method = RequestMethod.GET)
    public ResponseVO deleteBatchOfEmployee(@RequestParam int id){
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(value="/employee/get/all", method = RequestMethod.GET)
    public ResponseVO getEmployees(){
        //返回结果中不包括用户
        return employeeService.getEmployees();
    }
}
