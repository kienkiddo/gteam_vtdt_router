package vn.gteam.app.config;

import vn.gteam.app.JsonPath;
import vn.gteam.lib.JsonUtils;
import vn.gteam.lib.commom.Debug;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Getter
@Component
public class ConfigData {
    private AppConfig appConfig;
    private AppReviewConfig appReviewConfig;

    @PostConstruct
    private void init(){
        try {
            this.appConfig = JsonUtils.parseFromFile(AppConfig.class, JsonPath.APP_CONFIG);
            this.appReviewConfig = JsonUtils.parseFromFile(AppReviewConfig.class, JsonPath.APP_REVIEW_CONFIG);


            Debug.warning("Windows ; version = " + this.appConfig.getWindows().getVersion() + " ; build = " + this.appConfig.getWindows().getBuild() + " ; url = " + this.appConfig.getWindows().getUrl());
            Debug.warning("Android ; version = " + this.appConfig.getAndroid().getVersion() + " ; build = " + this.appConfig.getAndroid().getBuild()+ " ; url = " + this.appConfig.getAndroid().getUrl());
            Debug.warning("Ios ; version = " + this.appConfig.getIos().getVersion() + " ; build = " + this.appConfig.getIos().getBuild()+ " ; url = " + this.appConfig.getIos().getUrl());

            Debug.warning("App review config = " + JsonUtils.toJson(this.appReviewConfig));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
