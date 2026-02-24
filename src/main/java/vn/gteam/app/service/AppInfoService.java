package vn.gteam.app.service;

import lombok.RequiredArgsConstructor;
import vn.gteam.app.balance.AssetServerBalance;
import vn.gteam.app.config.ConfigData;
import vn.gteam.app.entity.AppVersion;
import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.manager.AppVersionManager;
import vn.gteam.app.manager.GatewayServerManager;
import vn.gteam.app.model.Platform;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.response.message.client.AppInfoResponse;
import vn.gteam.app.response.message.client.GetAppInfoResponse;
import vn.gteam.app.response.message.client.GetAppUpgradeResponse;
import vn.gteam.lib.commom.Debug;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppInfoService {
    private final ConfigData configData;
    private final AppVersionManager appVersionManager;
    private final AssetServerBalance assetServerBalance;
    private final GatewayServerManager gatewayServerManager;

    public BaseResponse perform(String version, int build, int platformType){
        Debug.log("client type = " + platformType + " ; version = " + version + " ; build = " + build);
        Platform platform = this.configData.getAppConfig().getByPlatformType(platformType);
        if (platform == null){
            platform = this.configData.getAppConfig().getWindows();
        }
        BaseResponse response = null;
        if (platform.getBuild() > build){
            response = this.getUpgradeInfo(platform);
        } else {
            return this.getAppInfo();
        }
        return response;
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
