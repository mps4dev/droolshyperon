package pl.vig.droolshyperon.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.format;

@Aspect
@Configuration
public class LoggingAspect {

    @Pointcut("execution(* pl.vig.droolshyperon.service.ApplicationService.service(..))")
    private void onService() {}

    @Around("onService()")
    public Object logServiceTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object ret = pjp.proceed();
        System.out.println(format("Performance time %d ms", System.currentTimeMillis() - start));
        return ret;
    }

}
