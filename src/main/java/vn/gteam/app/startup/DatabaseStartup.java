package vn.gteam.app.startup;

import vn.gteam.app.entity.AppVersion;
import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.manager.AppVersionManager;
import vn.gteam.app.properties.DefaultAppProperties;
import vn.gteam.app.repository.AppVersionRepository;
import vn.gteam.app.repository.AssetServerRepository;
import vn.gteam.app.repository.GatewayServerRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseStartup{
    private final DefaultAppProperties defaultAppProperties;
    private final AppVersionRepository appInfoRepository;
    private final AssetServerRepository assetServerRepository;
    private final GatewayServerRepository gatewayServerRepository;

    public DatabaseStartup(
            DefaultAppProperties defaultAppProperties,
            AppVersionRepository appVersionRepository,
            AssetServerRepository assetServerRepository,
            GatewayServerRepository gatewayServerRepository
    ){
        this.defaultAppProperties = defaultAppProperties;
        this.appInfoRepository = appVersionRepository;
        this.assetServerRepository = assetServerRepository;
        this.gatewayServerRepository = gatewayServerRepository;
    }

    public void init(){
        this.initAppInfo();
        this.initAssetServer();
        this.initGatewayServer();
    }

    private void initAppInfo(){
        var entity = this.appInfoRepository.findById(AppVersionManager.APP_INFO_PRIMARY_KEY);
        if (entity.isPresent()){
            return;
        }
        AppVersion appVersion = new AppVersion();
        appVersion.setAssetVersion(this.defaultAppProperties.getAssetVersion());
        this.appInfoRepository.save(appVersion);
    }

    private void initAssetServer(){
        var count = this.assetServerRepository.count();
        if (count > 0){
            return;
        }
        AssetServer assetServer = new AssetServer();
        this.assetServerRepository.save(assetServer);
    }

    private void initGatewayServer(){
        var count = this.gatewayServerRepository.count();
        if (count > 0){
            return;
        }
        GatewayServer gatewayServer = new GatewayServer();
        this.gatewayServerRepository.save(gatewayServer);
    }
}
