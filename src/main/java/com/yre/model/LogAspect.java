package com.yre.model;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
       @Autowired
       private  MongoTemplate    mongoTemplate;

 		public MongoTemplate getMongoTemplate() {
			return mongoTemplate;
		}
		public void setMongoTemplate(MongoTemplate mongoTemplate) {
			this.mongoTemplate = mongoTemplate;
		}


		   @Pointcut("execution(* com.jk.demo.service..*.query*(..))")
		 public  void logPointcut(){}
		 /** 
		  * 添加业务逻辑方法切入点 
		  */  
		 @Pointcut("execution(* com.jk.demo.service..*.add*(..))")
		 public void insertCell() {}  
		      
		 
		 /** 
		  * 修改业务逻辑方法切入点 
		  */  
		 @Pointcut("execution(* com.jk.demo.service..*.update*(..))")
		 public void updateCell() {}
		 
		 
		 /** 
		  * 删除业务逻辑方法切入点 
		  */  
		 @Pointcut("execution(* com.jk.demo.service..*.delete*(..))")
		 public void deleteCell() {} 
		    

		 @AfterReturning(value="logPointcut()",returning="rtv")
		    public void queryLog(JoinPoint JoinPoint,Object rtv){
			    //判断参数
		    	if(JoinPoint.getArgs()==null) {
		    		return;
		    	}
		    	Object[] os=JoinPoint.getArgs();
		    	//获取类名
		    	String className = JoinPoint.getTarget().getClass().getSimpleName();
		    	//获取方法名
		    	String methodName = JoinPoint.getSignature().getName();
		    	//获取参数列表
		    	String param=className+"."+methodName+":";
		    	//循环参数列表
		    	for (int i = 0; i < os.length; i++) {
		    		param+="参数["+i+"]"+os[i].toString();
				}
		    	//获取方法名
		    	//String name = JoinPoint.getSignature().getName();
		    	//
		    	Log  logBean  =  new  Log();
		    	Date date = new Date();
		    	logBean.setClassname(className);
		    	logBean.setMethodname(methodName);
		    	logBean.setCreateTime(date);
		    	logBean.setUserid("2222");
		    	System.out.println("类名:"+className);
		    	System.out.println("方法名:"+methodName);
		    	System.out.println("参数:"+param);
		    	System.out.println("操作时间:"+date);
		    	mongoTemplate.save(logBean) ;
		    }
		 

		    @AfterReturning(value = "insertCell()",  returning = "rtv")
		    public void insertLog(JoinPoint joinPoint, Object rtv) throws Throwable {  
		        // Admin admin=(Admin)  
		        // request.getSession().getAttribute("businessAdmin");  
		        // 判断参数  
		        if (joinPoint.getArgs() == null) {// 没有参数  
		            return;  
		        }  
		        //获取类名
		        String className = joinPoint.getTarget().getClass().getSimpleName();
		        // 获取方法名  
		        String methodName = joinPoint.getSignature().getName();  
		        // 获取操作内容  
		        String opContent = optionContent(joinPoint.getArgs(), methodName);  
		  
		        Log log = new Log();  
		        Date date = new Date();
		        
		        log.setClassname(className);
		        log.setMethodname(methodName);
		        log.setCreateTime(date);
		        log.setUserid("4444");
		        
		        System.out.println("类名:"+className);
		        System.out.println("方法名:"+methodName);
		        System.out.println("操作内容:"+opContent);
		        System.out.println("操作时间:"+date);
		        
		        mongoTemplate.save(log);
		    }  
		 

		    @AfterReturning(value = "updateCell()", returning = "rtv")
		    public void updateLog(JoinPoint joinPoint, Object rtv) throws Throwable {  
		        
		        // 判断参数  
		        if (joinPoint.getArgs() == null) {// 没有参数  
		            return;  
		        }  
		        Object[] args = joinPoint.getArgs();
		        //获取类名
		        String className = joinPoint.getTarget().getClass().getSimpleName();
		        // 获取方法名  
		        String methodName = joinPoint.getSignature().getName();  
		        // 获取操作内容  
		        String opContent = optionContent(joinPoint.getArgs(), methodName);  
		  
		        // 创建日志对象  
		        Log log = new Log();  
		        //获取当前时间
		        Date date = new Date();
		        
		        log.setClassname(className);
		        log.setMethodname(methodName);
		        log.setCreateTime(date);
		        log.setUserid("3333");
		        
		        System.out.println("类名:"+className);
		        System.out.println("方法名:"+methodName);
		        System.out.println("操作内容:"+opContent);
		        System.out.println("操作时间:"+date);
		        
		        mongoTemplate.save(log);
		        
		    }  
		    
		    

		    @AfterReturning(value = "deleteCell()", returning = "rtv")
		    public void deleteLog(JoinPoint joinPoint, Object rtv) throws Throwable {  
		        // Admin admin=(Admin)  
		        // request.getSession().getAttribute("businessAdmin");  
		        // 判断参数  
		        if (joinPoint.getArgs() == null) {// 没有参数  
		            return;  
		        }  
		        // 获取方法名  
		        String methodName = joinPoint.getSignature().getName(); 
		        //获取类名
		        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
		  
		        StringBuffer rs = new StringBuffer();  
		        rs.append(methodName);  
		        Object[] os = joinPoint.getArgs();
		        String className = null;  
		        for (Object info : joinPoint.getArgs()) {  
		            // 获取对象类型  
		            className = info.getClass().getName();  
		            className = className.substring(className.lastIndexOf(".") + 1);  
		            rs.append("[参数，类型:" + className + "，值:(id:"  
		                    + joinPoint.getArgs()[0] + ")");  
		        }  
		        //获取参数列表
		        String param=className+"."+methodName+":";
		        //循环参数
		    	for (int i = 0; i < os.length; i++) {
		    		param+="参数["+i+"]"+os[i].toString();
				}
		  
		        // 创建日志对象  
		        Log log = new Log();  
		        Date date = new Date();
		        
		        log.setClassname(simpleName);
		        log.setMethodname(methodName);
		        log.setCreateTime(date);
		        log.setUserid("1111");
		        
		        System.out.println("参数类型:"+className);
		        System.out.println("方法名:"+methodName);
		        System.out.println("类名:"+simpleName);
		        System.out.println("参数:"+param);
		        System.out.println("操作时间:"+date);
		        
		        mongoTemplate.save(log);
		        
		    }  
		    
	
	       
	       
		    /** 
		     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容 
		     *  
		     * @param args 
		     * @param mName 
		     * @return 
		     */  
		    public String optionContent(Object[] args, String mName) {  
		        if (args == null) {  
		            return null;  
		        }  
		        StringBuffer rs = new StringBuffer();  
		        rs.append(mName);  
		        String className = null;  
		        int index = 1;  
		        // 遍历参数对象  
		        for (Object info : args) {  
		            // 获取对象类型  
		            className = info.getClass().getName();  
		            className = className.substring(className.lastIndexOf(".") + 1);  
		            rs.append("[参数" + index + "，类型:" + className + "，值:");  
		            // 获取对象的所有方法  
		            Method[] methods = info.getClass().getDeclaredMethods();  
		            // 遍历方法，判断get方法  
		            for (Method method : methods) {  
		                String methodName = method.getName();  
		                // 判断是不是get方法  
		                if (methodName.indexOf("get") == -1) {// 不是get方法  
		                    continue;// 不处理  
		                }  
		                Object rsValue = null;  
		                try {  
		                    // 调用get方法，获取返回值  
		                    rsValue = method.invoke(info);  
		                } catch (Exception e) {  
		                    continue;  
		                }  
		                // 将值加入内容中  
		                rs.append("(" + methodName + ":" + rsValue + ")");  
		            }  
		            rs.append("]");  
		            index++;  
		        }  
		        return rs.toString();  
		    }

}
