package com.witch.pan.aspect;

import com.witch.common.pojo.BizException;
import com.witch.common.pojo.ResultCode;
import com.witch.pan.annotation.LoginValidator;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.vo.SessionWebUserVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 登陆效验切面
 * @author Yuuki
 */

@Aspect
@Component
public class LoginAspect {

    //切点  切面匹配表达式 这两个注解
    @Pointcut("@annotation(com.witch.pan.annotation.LoginValidator) || @within(com.witch.pan.annotation.LoginValidator)")
    private void pointCut() {}

    //这个方法会在当前包下的所有方法执行之前 执行  切面
    @Before("pointCut()")
    public void interceptorDo(JoinPoint point) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();

        // 获取目标对象
        //Object target = point.getTarget();

        // 获取方法上的LoginValidator注解
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上的LoginValidator注解
        LoginValidator loginValidator = method.getAnnotation(LoginValidator.class);
        // 如果有，并且值为false，则不校验
        if (loginValidator != null && !loginValidator.validated()) {
            return;
        }

        SessionWebUserVO userVo = (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
        if (null == userVo) {
            throw new BizException(ResultCode.LOGIN_AUTH_FAIL);
        }
        if (loginValidator != null && loginValidator.checkAdmin() && !userVo.getIsAdmin()) {
            throw new BizException(ResultCode.NO_PERMISSION);
        }
    }
}
