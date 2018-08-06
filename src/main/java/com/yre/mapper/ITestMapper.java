package com.yre.mapper;

import com.yre.model.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ITestMapper extends JpaRepository<Employee,Serializable>{

  @Query(value = "SELECT * FROM emp_employee",nativeQuery = true)
    List<Employee> queryEmployeeList();

}
