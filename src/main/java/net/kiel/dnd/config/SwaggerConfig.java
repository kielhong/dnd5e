package net.kiel.dnd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@Configuration
@EnableSwagger
public class SwaggerConfig {
    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;
    
    @Bean   
    public SwaggerSpringMvcPlugin customImplementation(){
       return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
             .apiInfo(apiInfo())
             .includePatterns(".api.*");
    }
    
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "D&D 5th Edition",
                "D&D 5E Character Sheet",
                null,
                null,
                null,
                null);
        
        return apiInfo;
    }
}
