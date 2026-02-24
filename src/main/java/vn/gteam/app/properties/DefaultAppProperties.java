package vn.gteam.app.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "default")
@Getter
@Setter
public class DefaultAppProperties {
    private int assetVersion;
}
