package com.yre.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emp_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer empid;//
    @Column
    private String empname;//
    @Column
    private Integer empsex;//
    @Column
    private Integer empage;//
    @Column
    private String empaddress;//

    @Column
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Integer getEmpsex() {
        return empsex;
    }

    public void setEmpsex(Integer empsex) {
        this.empsex = empsex;
    }

    public Integer getEmpage() {
        return empage;
    }

    public void setEmpage(Integer empage) {
        this.empage = empage;
    }

    public String getEmpaddress() {
        return empaddress;
    }

    public void setEmpaddress(String empaddress) {
        this.empaddress = empaddress;
    }

}
