package vn.gteam.app.response.message.client;

import vn.gteam.app.entity.AppVersion;
import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.response.entity.AppVersionResponseEntity;
import vn.gteam.app.response.entity.AssetServerResponseEntity;
import vn.gteam.app.response.entity.GatewayServerResponseEntity;
import lombok.Getter;
import lombok.Setter;
import vn.gteam.reflect.MyReflection;

@Getter
@Setter
@MyReflection
public class GetAppInfoResponse extends AppInfoResponse {
    private AppVersionResponseEntity info;
    private AssetServerResponseEntity assetServer;
    private GatewayServerResponseEntity gatewayServer;

    public GetAppInfoResponse() {
        super(GET_APP_INFO);
    }

    public void fill(AppVersion appVersion, AssetServer assetServer, GatewayServer gatewayServer){
        this.info = new AppVersionResponseEntity();
        this.info.fill(appVersion);

        this.assetServer = new AssetServerResponseEntity();
        this.assetServer.fill(assetServer);

        this.gatewayServer = new GatewayServerResponseEntity();
        this.gatewayServer.fill(gatewayServer);
    }
}
