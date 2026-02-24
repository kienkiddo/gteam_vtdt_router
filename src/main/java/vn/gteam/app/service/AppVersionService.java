package vn.gteam.app.service;


import vn.gteam.app.entity.AppVersion;
import vn.gteam.app.manager.AppVersionManager;
import vn.gteam.app.repository.AppVersionRepository;
import vn.gteam.app.response.BaseResponse;
import org.springframework.stereotype.Component;

@Component
public class AppVersionService {
    private final AppVersionManager appVersionManager;
    private final AppVersionRepository appVersionRepository;

    public AppVersionService(AppVersionManager appVersionManager, AppVersionRepository appVersionRepository){
        this.appVersionManager = appVersionManager;
        this.appVersionRepository = appVersionRepository;
    }

    public BaseResponse newAssetVersion(){
        AppVersion appVersion = this.appVersionManager.getAppVersion();
        appVersion.setAssetVersion(appVersion.getAssetVersion() + 1);
        this.appVersionRepository.save(appVersion);

        return (new BaseResponse()).setSuccess();
    }
}
