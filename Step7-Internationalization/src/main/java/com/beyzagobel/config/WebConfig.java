package com.beyzagobel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.beyzagobel")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver jspResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("text", "plain", Charset.forName("UTF-8")));
        mediaTypes.add(new MediaType("text", "html", Charset.forName("UTF-8")));
        mediaTypes.add(new MediaType("application", "json", Charset.forName("UTF-8")));
        mediaTypes.add(new MediaType("text", "javascript", Charset.forName("UTF-8")));

        stringConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(stringConverter);

    }

    /*
     * Java tabanlı Resource Bundle 'message' konfigürasyonu
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");  // classpath = resources
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
    }

    /*
     * 1- Oturum locale bilgisinin çözümlenmesi için SessionLocaleResolver bean'i veya CookieLocaleResolve bean'i WebConfig'de tanımlanmalıdır.
     * CookieLocaleResolver farklı olarak locale bilgisini çözümlemek dışında depolaktadır.
     */
    /*
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag("tr-TR"));  // varsayılan locale bilgisi
        localeResolver.setCookieName("bm470-locale-cookie");  // locale bilgisinin depolandığı cookie ismi
        localeResolver.setCookieMaxAge(3600); // çerezin geçerlilik süresi (saniye)
        return localeResolver;
    }

     */

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag("tr-TR"));  //varsayılan locale(dil) bilgisi tanımlanır.
        return localeResolver;
    }

    /*
     * 2- Oturumun locale bilgisinin değişimini izlemek ve ona göre dil dil mesaj çözümlemesini sağlamak için LocaleChangeInteceptor bean'i tanımlanır.
     */
    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");  // locale değişimini tetikleyecek istek(request) parametresidir.
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localeInterceptor()).addPathPatterns("/*");  // 3- localeInterceptor eklenir.

    }


}
