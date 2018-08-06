package com.yre.service;

import com.yre.model.Employee;

import java.util.List;

public interface ITestService {


    List<Employee> queryEmployeeList();

    Employee getEmpById(int i);

    void saveEmployee(Employee emp);

    void updateEmployee(Employee emp);

    void deleteEmplloyee(Employee emp);

    List<Employee> queryEmpListMYBatis();

    void saveEmpMyBatis(Employee emp);

    void updateEmpMyBatis(Employee emp);

    void deleteEmpMyBatis(Employee emp);

    List<Employee> queryredismongodb();
}
