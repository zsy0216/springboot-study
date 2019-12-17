package com.tassel.dao;

import com.tassel.pojo.Department;
import com.tassel.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    // 模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "AA", "AA2345@qq.com", 1, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "BB", "BB2345@qq.com", 0, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CC", "CC2345@qq.com", 1, new Department(103, "财务部")));
        employees.put(1004, new Employee(1004, "DD", "DD2345@qq.com", 0, new Department(104, "教研部")));
        employees.put(1005, new Employee(1005, "EE", "EE2345@qq.com", 1, new Department(105, "行政部")));
    }

    //自增主键
    private static Integer initId = 1006;

    // 增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    // 查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployee(Integer id){
        return employees.get(id);
    }

    // 通过id删除员工
    public void delete(Integer id){
        employees.remove(id);
    }
}
