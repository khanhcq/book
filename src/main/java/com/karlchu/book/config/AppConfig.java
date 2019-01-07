package com.karlchu.book.config;

import com.karlchu.book.common.WebSiteMeshFilter;
import org.displaytag.filter.ResponseOverrideFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.util.Locale;

/**
 * Created by KhanhChu on 12/31/2018.
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<WebSiteMeshFilter> siteMeshFilter(){
        FilterRegistrationBean<WebSiteMeshFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new WebSiteMeshFilter());
        filter.addUrlPatterns("/*");
        return filter;
    }

    @Bean
    public FilterRegistrationBean<ResponseOverrideFilter> loggingFilter(){
        FilterRegistrationBean<ResponseOverrideFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ResponseOverrideFilter());
        registrationBean.addUrlPatterns("*.html");

        return registrationBean;
    }

    @Bean
    public LocaleResolver localeResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);

        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("vi", "VI"));
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("ApplicationResources");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<ServletContextListener> bean =
                new ServletListenerRegistrationBean<>();
        bean.setListener(new StartupListener());
        return bean;
    }

    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter("javax.servlet.jsp.jstl.fmt.localizationContext", "ApplicationResources");
            }
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
