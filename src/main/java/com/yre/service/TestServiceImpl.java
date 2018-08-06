package com.yre.service;

import com.yre.mapper.IEmpMapper;
import com.yre.mapper.ITestMapper;
import com.yre.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService{

    @Autowired
    private ITestMapper testMapper;

    @Resource
    private IEmpMapper empMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Employee> queryEmployeeList() {
        return testMapper.queryEmployeeList();
    }

    /**
     * 使用jdbcTemplate进行查询
     * @param i
     * @return
     */
    @Override
    public Employee getEmpById(int i) {
        String sql = "select * from emp_employee where empid = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
        return jdbcTemplate.queryForObject(sql, rowMapper,i);
    }

    @Override
    public void saveEmployee(Employee emp) {
        String sql = "INSERT INTO emp_employee (empid,empaddress,empage,empname,empsex,create_time) VALUES ("+emp.getEmpid()+",'"+emp.getEmpaddress()+"',"+emp.getEmpage()+",'"+emp.getEmpname()+"',"+emp.getEmpsex()+",'"+emp.getCreateTime().toLocaleString()+"')";
        System.out.print("使用jdbcTemplate进行新增成功");
        System.out.print(sql);
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE emp_employee SET empaddress = ?,empage = ?,empname = ?,empsex = ?,create_time = ? WHERE  empid = ?";
        Object str[]={emp.getEmpaddress(),emp.getEmpage(),emp.getEmpname(),emp.getEmpsex(),emp.getCreateTime(),emp.getEmpid()};
        System.out.print("使用jdbcTemplate进行修改成功");
        jdbcTemplate.update(sql,str);
    }

    @Override
    public void deleteEmplloyee(Employee emp) {
        String sql = "DELETE FROM emp_employee WHERE  empid = ?";
        Object str[]={emp.getEmpid()};
        System.out.print("使用jdbcTemplate进行删除成功");
        jdbcTemplate.update(sql,str);
    }

    @Override
    public List<Employee> queryEmpListMYBatis() {
        return empMapper.queryEmpListMYBatis();
    }

    @Override
    public void saveEmpMyBatis(Employee emp) {
        empMapper.saveEmpMyBatis(emp);
    }

    @Override
    public void updateEmpMyBatis(Employee emp) {
        empMapper.updateEmpMyBatis(emp);
    }

    @Override
    public void deleteEmpMyBatis(Employee emp) {
        empMapper.deleteEmpMyBatis(emp);
    }


    @Override
    public List<Employee> queryredismongodb(){
        redisTemplate.opsForValue().set("queryredismongodb","tetx");
        String str=" select * from emp_employee";
        List<Employee> list= empMapper.queryEmpListMYBatis();
            for (Employee modele : list) {
            mongoTemplate.save(modele);
        }
        List<Employee> list1 = mongoTemplate.find(new Query(), Employee.class);
        System.out.println("++++++++monfgasiojhdjkahsjkdhjka"+list1.toString());

        return list;
    }


}
