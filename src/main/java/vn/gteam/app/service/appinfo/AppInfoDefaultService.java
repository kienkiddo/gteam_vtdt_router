package vn.gteam.app.service.appinfo;

import lombok.RequiredArgsConstructor;
import vn.gteam.app.balance.AssetServerBalance;
import vn.gteam.app.config.AppReviewConfig;
import vn.gteam.app.config.ConfigData;
import vn.gteam.app.entity.AppVersion;
import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.manager.AppVersionManager;
import vn.gteam.app.manager.GatewayServerManager;
import vn.gteam.app.model.Platform;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.response.message.client.GetAppInfoResponse;
import vn.gteam.app.response.message.client.GetAppUpgradeResponse;
import vn.gteam.lib.commom.Debug;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppInfoDefaultService implements AppInfoService {
    private final ConfigData configData;
    private final AppVersionManager appVersionManager;
    private final AssetServerBalance assetServerBalance;
    private final GatewayServerManager gatewayServerManager;

    @Override
    public BaseResponse perform(String version, int build, int platformType){
        Platform platform = this.configData.getAppConfig().getByPlatformType(platformType);
        if (platform == null){
            platform = this.configData.getAppConfig().getWindows();
        }
        if (platform.getBuild() > build){
            return this.getUpgradeInfo(platform);
        }
        return this.getAppInfo();
    }

    private BaseResponse getUpgradeInfo(Platform platform){
        GetAppUpgradeResponse appInfoResponse = new GetAppUpgradeResponse();
        appInfoResponse.setPlatform(platform);
        return appInfoResponse.setSuccess();
    }

    private BaseResponse getAppInfo(){
        AppVersion appVersion = this.appVersionManager.getAppVersion();
        AssetServer assetServer = this.assetServerBalance.getNextSlaveServer();
        GatewayServer gatewayServer = this.gatewayServerManager.getFirst();

        GetAppInfoResponse appInfoResponse = new GetAppInfoResponse();
        appInfoResponse.fill(appVersion, assetServer, gatewayServer);
        appInfoResponse.setSuccess();
        return appInfoResponse;
    }



}
