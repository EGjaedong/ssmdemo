package cn.webdemo.ssm.controller;

import cn.webdemo.ssm.domain.Syslog;
import cn.webdemo.ssm.service.ProducerService;
import cn.webdemo.ssm.service.SysLogService;
import org.apache.activemq.command.ActiveMQQueue;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.print.attribute.standard.Destination;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by 63042 on 2019/7/18.
 * 日志记录，包括前置和后置通知
 * 填充日志类，字段如下：
 *  visitTime 当前时间
 *  username security中获得
 *  ip request对象中
 *  url 读取注解的值
 *  executionTime 操作时长
 *  method 反射获得
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private ProducerService producerService;

    private Date visitTime; // 操作开始时间
    private Class clazz; // 访问的类
    private Method method; // 访问的方法

    /**
     * 前置通知
     * 获取开始时间，执行的类和方法
     * @param jp
     */
    @Before("execution(* cn.webdemo.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); // 当前时间就是操作开始时间
        clazz = jp.getTarget().getClass(); // getTarget()获取当前被代理的对象
        String methodName = jp.getSignature().getName(); // 获取当前方法名
        Object[] args = jp.getArgs(); // 获取方法的参数列表

        // 获取当前执行方法的method对象
        if (args == null || args.length == 0){
            // 获取无参数的方法
            method = clazz.getMethod(methodName);
        }
        else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* cn.webdemo.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        long time = new Date().getTime() - visitTime.getTime(); // 获取访问时长

        String url = "";
        // 获取url
        if (clazz != null && method != null && clazz != LogAop.class){
            // 1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                String[] classAnnoValues = classAnnotation.value();
                // 2.获取方法上的@RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodAnnoValues = methodAnnotation.value();
                    // 3.拼接url
                    url = classAnnoValues[0] + methodAnnoValues[0];

                    // 获取访问ip
                    String ip = request.getRemoteAddr();

                    // 获取当前操作用户，从上下文中获取当前登录用户
                    // 第一种方式：
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    // 第二种方式：
                    // SecurityContext context1 = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

                    // 封装到SysLog中
                    Syslog log = new Syslog();
                    log.setVisitTime(visitTime);
                    log.setExecutionTime(time);
                    log.setIp(ip);
                    log.setUrl(url);
                    log.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    log.setUsername(username);

                    // 调用service存到数据库中
                    //sysLogService.save(log);
                    producerService.sendMessage(log);
                }
            }
        }
    }
}
