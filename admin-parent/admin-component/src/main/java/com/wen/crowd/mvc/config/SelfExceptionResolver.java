package com.wen.crowd.mvc.config;

import com.google.gson.Gson;
import com.wen.crowd.constant.CrowdConstant;
import com.wen.crowd.exception.AccessForbiddenException;
import com.wen.crowd.exception.LoginAcctAlreadyInUseException;
import com.wen.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.wen.crowd.exception.LoginFailedException;
import com.wen.crowd.util.JudgeRequestType;
import com.wen.crowd.util.ResultEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wen
 * @create 2021 1月 16 星期六 12:47
 * @description 表示当前类是基于注解的异常处理类
 */
@ControllerAdvice
public class SelfExceptionResolver {
    //将一个异常类型和一个具体的方法关联起来,登录失败异常
    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseForUpdateException(
            //实际捕获到的异常
            LoginAcctAlreadyInUseForUpdateException exception,
            //请求对象
            HttpServletRequest request,
            //响应对象
            HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";
        return commonResolver(viewName, exception, request, response);
    }
    //将一个异常类型和一个具体的方法关联起来,登录失败异常
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            //实际捕获到的异常
            LoginAcctAlreadyInUseException exception,
            //请求对象
            HttpServletRequest request,
            //响应对象
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-add";
        return commonResolver(viewName, exception, request, response);
    }
    //将一个异常类型和一个具体的方法关联起来,登录失败异常
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            //实际捕获到的异常
            LoginFailedException exception,
            //请求对象
            HttpServletRequest request,
            //响应对象
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-login";
        return commonResolver(viewName, exception, request, response);
    }

    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(
            //实际捕获到的异常
            AccessForbiddenException exception,
            //请求对象
            HttpServletRequest request,
            //响应对象
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-login";
        return commonResolver(viewName, exception, request, response);
    }

    private static ModelAndView commonResolver(
            //异常处理后要跳转的页面
            String viewName,
            //实际捕获到的异常
            Exception exception,
            //请求对象
            HttpServletRequest request,
            //响应对象
            HttpServletResponse response
    ) throws IOException {
        //1、判断当前请求类型
        boolean requestType = JudgeRequestType.judgeRequestType(request);

        //2、如果是Ajax请求
        if (requestType) {
            //3、创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.filed(exception.getMessage());
            //4、创建Gson对象
            Gson gson = new Gson();
            //5、将ResultEntity对象转换为Json字符串
            String json = gson.toJson(resultEntity);
            //6、将Json字符串作为响应体响应给浏览器
            response.getWriter().write(json);
            //7、由于已经通过原生的response对象返回了响应，就不提供ModelAndView页面
            return null;
        }
        //8、如果不是Ajax请求，就新建ModelAndView页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
