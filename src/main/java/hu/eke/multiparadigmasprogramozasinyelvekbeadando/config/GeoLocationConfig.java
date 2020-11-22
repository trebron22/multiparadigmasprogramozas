package hu.eke.multiparadigmasprogramozasinyelvekbeadando.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "geo-location-config")
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class GeoLocationConfig {
    private String BASE_URL;
    private String API_KEY;
    private String URI;
}
