package com.tassel.controller;

import com.tassel.dao.DepartmentDao;
import com.tassel.dao.EmployeeDao;
import com.tassel.pojo.Department;
import com.tassel.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
        //根据id查询出员工信息
        Employee employee = employeeDao.getEmployee(id);
        model.addAttribute("emp", employee);

        // 查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);

        return "emp/update";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // @DeleteMapping("/emp/{id}")
    @GetMapping("/emp/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }


}
