package com.tassel.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义国际化
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 获取请求中的语言参数
        String language = httpServletRequest.getParameter("l");
        // 没有语言参数则使用默认的
        Locale locale = Locale.getDefault();
        // 如果语言参数不为空
        if(!StringUtils.isEmpty(language)){
            // zh_CN
            String[] split = language.split("_");
            // 国家，地区
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        this.setLocale(httpServletRequest, httpServletResponse, locale);
    }
}
