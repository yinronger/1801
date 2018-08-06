package com.yre.controller;

import com.yre.model.Employee;
import com.yre.service.ITestService;
import com.yre.service.TestServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {

    @Resource
    private ITestService testService;

    @RequestMapping("toIndex")
    public String toIndex(){
        return "index";
    }

    /**
     * 检测fastjson 用法
     * @return
     */
    @RequestMapping("queryEmployeeListJpa")
    @ResponseBody
    public List<Employee> queryEmployeeList(){
        return testService.queryEmployeeList();
    }

    /**
     * 使用jdbcTemplate进行查询
     * @return
     */
    @RequestMapping("/getEmpById")
    @ResponseBody
    public  Employee getEmpById() {
        Employee  emp =  testService.getEmpById(1);
        return emp;
    }

    /**
     * 使用jdbcTemplate进行新增
     */
    @RequestMapping("saveEmployee")
    @ResponseBody
    public void saveEmployee(){
        Employee emp = new Employee();
        emp.setEmpid(7);
        emp.setEmpname("萧红");
        emp.setEmpage(20);
        emp.setEmpsex(1);
        emp.setEmpaddress("安徽省");
        Date createtime = new Date();
        emp.setCreateTime(createtime);
        testService.saveEmployee(emp);
    }

    /**
     *使用jdbcTemplate进行修改
     */
    @RequestMapping("updateEmployee")
    @ResponseBody
    public void updateEmployee(Employee emp2){
        Employee emp = new Employee();
        emp.setEmpname("修改了杨幂76");
        emp.setEmpid(3);
        emp.setEmpage(32);
        emp.setEmpsex(1);
        emp.setEmpaddress("修改了安徽省2");
        Date createtime = new Date();
        emp.setCreateTime(createtime);
        testService.updateEmployee(emp);
    }


    /**
     * 使用jdbcTemplate进行删除
     */
    @RequestMapping("deleteEmplloyee")
    @ResponseBody
    public void deleteEmplloyee(){
        Employee emp = new Employee();
        emp.setEmpid(8);
        testService.deleteEmplloyee(emp);
    }

    /**
     * 使用mybatis进行查询
     * @return
     */
    @RequestMapping("queryEmpListMYBatis")
    @ResponseBody
    public List<Employee> queryEmpListMYBatis(){
        return testService.queryEmpListMYBatis();
    }

    /**
     *用mybatis进行新增
     */
    @RequestMapping("saveEmpMyBatis")
    @ResponseBody
    public void saveEmpMyBatis(){
        Employee emp = new Employee();
        emp.setEmpname("MyBatis新增杨幂");
        emp.setEmpid(16);
        emp.setEmpage(32);
        emp.setEmpsex(1);
        emp.setEmpaddress("MyBatis新增了安徽省");
        testService.saveEmpMyBatis(emp);
    }

    /**
     *用mybatis进行修改
     */
    @RequestMapping("updateEmpMyBatis")
    @ResponseBody
    public void updateEmpMyBatis(){
        Employee emp = new Employee();
        emp.setEmpname("MyBatis修改杨幂");
        emp.setEmpid(11);
        emp.setEmpage(32);
        emp.setEmpsex(1);
        emp.setEmpaddress("MyBatis修改了安徽省");
        testService.updateEmpMyBatis(emp);
    }

    /**
     *用mybatis进行删除
     */
    @RequestMapping("deleteEmpMyBatis")
    @ResponseBody
    public void deleteEmpMyBatis(){
        Employee emp = new Employee();
        emp.setEmpid(11);
        testService.deleteEmpMyBatis(emp);
    }

    /**
     * redis 和 mongodb
     * @return
     */
    @RequestMapping("queryredismongodb")
    @ResponseBody
    public List<Employee> queryredismongodb(){
        return testService.queryredismongodb();
    }



}
