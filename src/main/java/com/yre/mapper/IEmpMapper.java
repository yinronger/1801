package com.yre.mapper;

import com.yre.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IEmpMapper {

    @Select("select * from emp_employee")
    List<Employee> queryEmpListMYBatis();

    @Insert("insert into emp_employee(empid,empaddress,empage,empname,empsex,create_time) values (#{empid},#{empaddress},#{empage},#{empname},#{empsex},now())")
    void saveEmpMyBatis(Employee emp);

    @Update("update emp_employee set empaddress=#{empaddress},empage=#{empage},empname=#{empname},empsex=#{empsex},create_time=now() where empid=#{empid}")
    void updateEmpMyBatis(Employee emp);

    @Delete("delete from emp_employee where empid=#{empid}")
    void deleteEmpMyBatis(Employee emp);
}
