package vn.gteam.app.service.common;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vn.gteam.app.config.AppReviewConfig;
import vn.gteam.app.config.ConfigData;

import java.util.Set;

@Component
@AllArgsConstructor
public class AppReviewServiceImpl implements AppReviewService{
    private final ConfigData configData;

    @Override
    public boolean match(String version, int build, int platformType) {
        AppReviewConfig config = this.configData.getAppReviewConfig();
        if (config.getMatches() != null && config.getMatches().containsKey(platformType)){
            Set<String> versions = config.getMatches().get(platformType);
            return versions.contains(version);
        }
        return false;
    }
}
