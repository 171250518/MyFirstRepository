package nju.edu.cinemaSystem.data.management;


import nju.edu.cinemaSystem.po.Employee;
import nju.edu.cinemaSystem.vo.EmployeeForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    /**
     * 插入一条职员信息
     * @param username
     * @param password
     * @param identity
     * @return
     */
    int insertOneEmployee(@Param("username") String username, @Param("password") String password ,@Param("identity") int identity);

    /**
     * 根据id查找职员
     * @param id
     * @return
     */
    Employee selectEmployeeById(@Param("id") int id);


    /**
     * 展示所有职员(不包括用户)
     * @return
     */
    List<Employee> selectEmployees();

    /**
     * 批量删除职员信息
     * @param employeeIdList
     * @return
     */
    int deleteEmployeeBatch(List<Integer> employeeIdList);


    /**
     * 修改职员信息
     * @param updateEmployeeForm
     * @return
     */
    int updateEmployee(EmployeeForm updateEmployeeForm);

}
