package com.beyzagobel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



// Konfigürasyon Sınıfı
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.beyzagobel"})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8");

        return viewResolver;
    }

    /*
     * Static kaynak tanımlarının yapıldığı dosya yolunu (path) belirtmek için
     * WebMvcConfigurer interface'inin addResourceHandlers metodu implemente edilir.
     * Örn : /resources/deneme.png dosyasına yapılan istek webapp/resources/images/deneme.png yoluna yönlendirilir.
     */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

   }

}
