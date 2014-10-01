package net.kiel.dnd.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class PropertyConfig {
    private static final String ENV_LOCAL = "local";
    private static final String ENV_PRODUCTION = "production";

    @Configuration
    @Profile(ENV_LOCAL)
    static class Localhost {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
            PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

            propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:properties/*.properties"));
            propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:properties/" + ENV_LOCAL + "/*.properties"));

            return propertySourcesPlaceholderConfigurer;
        }
    }

    @Configuration
    @Profile(ENV_PRODUCTION)
    static class Production {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
            PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

            propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:properties/*.properties"));
            propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:properties/" + ENV_PRODUCTION + "/*.properties"));

            return propertySourcesPlaceholderConfigurer;
        }
    }
}
