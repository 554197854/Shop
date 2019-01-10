package com.shop.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author N
 * @create 2019/1/8 -- 15:05
 * @email 554197854@qq.com
 */
public class GlobalException implements HandlerExceptionResolver {
    //全局的异常信息
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Logger logger =  LoggerFactory.getLogger(GlobalException.class);
        logger.info(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/exception");
        modelAndView.addObject("message","异常信息");
        return modelAndView;
    }
}
