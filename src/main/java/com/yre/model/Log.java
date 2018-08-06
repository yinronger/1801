package com.yre.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="StuStuStu")
public class Log implements Serializable{
	
	             private   static  final  long  serialVersionUID = 1L;//
	             private   String  id;//
	             private   String  classname;//
	             private   String  methodname;//
	             private   String  userid;//
	             private   String  ip;//
	             private   Date    createTime;//
				public String getId() {
					return id;
				}
				public void setId(String id) {
					this.id = id;
				}
				public String getClassname() {
					return classname;
				}
				public void setClassname(String classname) {
					this.classname = classname;
				}
				public String getMethodname() {
					return methodname;
				}
				public void setMethodname(String methodname) {
					this.methodname = methodname;
				}
				public String getUserid() {
					return userid;
				}
				public void setUserid(String userid) {
					this.userid = userid;
				}
				public String getIp() {
					return ip;
				}
				public void setIp(String ip) {
					this.ip = ip;
				}
				public Date getCreateTime() {
					return createTime;
				}
				public void setCreateTime(Date createTime) {
					this.createTime = createTime;
				}
				
				/* (non-Javadoc)    
				 * @see java.lang.Object#toString()    
				 */
				@Override
				public String toString() {
					return "Log [id=" + id + ", classname=" + classname + ", methodname=" + methodname + ", userid="
							+ userid + ", ip=" + ip + ", createTime=" + createTime + "]";
				}
	             

}
