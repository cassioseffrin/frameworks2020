package br.edu.cassio.uteis;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
	@Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryClassMethods() {};
 
    @Around("repositoryClassMethods()")
    public Object medirTempoExecucao(ProceedingJoinPoint joinPoint) 
      throws Throwable {
        long inicio = System.nanoTime();
        Object returnValue = joinPoint.proceed();
        long fim = System.nanoTime();
        String nomeMetodo = joinPoint.getSignature().getName();
        System.out.println(
          "Execução " + nomeMetodo + " levou " + 
          TimeUnit.NANOSECONDS.toMillis(fim - inicio) + " ms");
        return returnValue;
    }
}