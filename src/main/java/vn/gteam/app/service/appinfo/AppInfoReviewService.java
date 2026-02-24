package vn.gteam.app.service.appinfo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vn.gteam.app.config.AppReviewConfig;
import vn.gteam.app.config.ConfigData;
import vn.gteam.app.entity.AppVersion;
import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.manager.AppVersionManager;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.response.message.client.GetAppInfoResponse;
import vn.gteam.lib.commom.Debug;

@Component
@AllArgsConstructor
public class AppInfoReviewService implements AppInfoService{
    private final ConfigData configData;
    private final AppVersionManager appVersionManager;

    @Override
    public BaseResponse perform(String version, int build, int platformType) {
        return this.getAppleReviewAppInfo();
    }

    private BaseResponse getAppleReviewAppInfo(){
        AppReviewConfig config = this.configData.getAppReviewConfig();
        AppVersion appVersion = this.appVersionManager.getAppVersion();
        AssetServer assetServer = config.getAssetServer();
        GatewayServer gatewayServer = config.getGatewayServer();

        GetAppInfoResponse appInfoResponse = new GetAppInfoResponse();
        appInfoResponse.fill(appVersion, assetServer, gatewayServer);
        appInfoResponse.setSuccess();
        return appInfoResponse;
    }
}
