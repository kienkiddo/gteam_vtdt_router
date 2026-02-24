package vn.gteam.app.manager;

import vn.gteam.app.entity.AppVersion;
import vn.gteam.app.model.ILoader;
import vn.gteam.app.repository.AppVersionRepository;
import vn.gteam.lib.commom.Debug;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class AppVersionManager implements ILoader {
    private final AppVersionRepository appVersionRepository;

    @Getter
    private AppVersion appVersion;

    public AppVersionManager(AppVersionRepository appVersionRepository){
        this.appVersionRepository = appVersionRepository;
    }

    @Override
    public void load() {
        this.appVersion = this.appVersionRepository.findById(APP_INFO_PRIMARY_KEY).get();
        Debug.log(this, 1);
    }


    public static final int APP_INFO_PRIMARY_KEY = 1;
}
