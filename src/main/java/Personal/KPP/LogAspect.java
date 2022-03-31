package Personal.KPP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static StopWatch stopWatch = new StopWatch();

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint jointPoint) throws Throwable {
        //StopWatch stopWatch = new StopWatch();

        stopWatch.start("save");

        Object proceed = jointPoint.proceed();

        stopWatch.stop();

        return proceed;
    }

    public void printLog(){
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        logger.info("total: {}", totalTimeMillis);
    }

}
