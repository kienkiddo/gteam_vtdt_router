package vn.gteam.app.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vn.gteam.app.config.AppReviewConfig;
import vn.gteam.app.config.ConfigData;
import vn.gteam.app.service.appinfo.AppInfoReviewService;
import vn.gteam.app.service.appinfo.AppInfoService;
import vn.gteam.app.service.appinfo.AppInfoDefaultService;
import vn.gteam.app.service.common.AppReviewService;

import java.util.Set;

@Component
@AllArgsConstructor
public class AppInfoServiceFactoryImpl implements AppInfoServiceFactory{
    private final AppReviewService appReviewService;
    private final AppInfoDefaultService appInfoDefaultService;
    private final AppInfoReviewService appInfoReviewService;


    @Override
    public AppInfoService make(String version, int build, int platformType) {
        if (this.appReviewService.match(version, build, platformType)) {
            return this.appInfoReviewService;
        }
        return this.appInfoDefaultService;
    }
}
