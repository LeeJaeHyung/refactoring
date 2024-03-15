package com.sparta.hmpah.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParameterAop {

  @Pointcut("execution(* com.sparta.hmpah.controller..*.*(..))")
  private void point() {}

  @Before("point()")
  public void before(JoinPoint joinPoint) {

    //실행되는 함수 이름을 가져오고 출력
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();
    System.out.println(method.getName() + "메서드 실행");

    //메서드에 들어가는 매개변수 배열을 읽어옴
    Object[] args = joinPoint.getArgs();

    //매개변수 배열의 종류와 값을 출력
    for(Object obj : args) {
      System.out.println("type : "+obj.getClass().getSimpleName());
      System.out.println("value : "+obj);
    }
  }

  //cut() 메서드가 종료되는 시점에 afterReturn() 메서드 실행
  //@AfterReturning 어노테이션의 returning 값과 afterReturn 매개변수 obj의 이름이 같아야 함
  @AfterReturning(value = "point()", returning = "obj")
  public void afterReturn(JoinPoint joinPoint, Object obj) {
    System.out.println("return obj");
    System.out.println(obj);
  }
}
