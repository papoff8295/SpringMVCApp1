package ru.popov.springcourse.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class SpringConfig {

//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("file:///D:\\project\\SpringMVCApp1\\src\\main\\java\\ru\\popov\\springcourse\\config\\messages\\app");
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(5); //reload every 5 sec..
//        messageSource.setFallbackToSystemLocale(false);
//        return messageSource;
//    }
}
