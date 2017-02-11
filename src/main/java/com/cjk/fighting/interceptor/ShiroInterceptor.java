package com.cjk.fighting.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class   ShiroInterceptor {
	
	@Pointcut("execution(* org.apache.shiro.realm..*.*(..))")
	public void pointCut() {
	}
	
	//Around("pointCut()")
	public Object showParameter(ProceedingJoinPoint pjp)
	{
		System.out.println("ShiroInterceptor::@Around");
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" 返回值："+result == null ? "空":result);
		return result;
	}
	
	@Before("pointCut()")
	public void beforeShiro(JoinPoint joinPoint)
	{
		System.out.println("ShiroInterceptor:@Before");
		Object obj= joinPoint.getTarget();
		Signature signature =joinPoint.getSignature();
		String methodName = signature.getName();
		
		System.out.println(obj.getClass().getName()+"."+methodName+"()");
		Class<?> cls= signature.getDeclaringType();
		Method [] meths = cls.getDeclaredMethods();
		for (Method method :meths)
		{
			if(methodName.equals(method.getName()))
			{
				Class<?> [] types = method.getParameterTypes();
				for (Class<?> type:types)
				{
					System.out.println("type:"+type.getName());
				}
			}
		}
		Object [] args = joinPoint.getArgs();
		System.out.println("参数：{{");
		for (Object arg : args)
		{
		   System.out.println(arg);
		}
		System.out.println("}}");
	}
	
	//After("pointCut()")
	public void afterShiro(JoinPoint joinPoint)
	{
		System.out.println("ShiroInterceptor:@After");
		Object [] args = joinPoint.getArgs();
		
		for (Object arg : args)
		{
		   System.out.println(arg);
		}
	}
	
	@AfterReturning(pointcut = "pointCut()", returning = "returnVal")
	public void afterRuning(JoinPoint joinPoint, Object returnVal) 
	{
		System.out.println("afterReturning executed, return result is "
				+ returnVal);
	}

}



