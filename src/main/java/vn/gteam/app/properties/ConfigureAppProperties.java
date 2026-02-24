package vn.gteam.app.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class ConfigureAppProperties {
    private String clientAssetServerSecret;
    private String clientGameServerSecret;

    private String coreGameServerSecret;
}
