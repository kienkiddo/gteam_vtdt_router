package vn.gteam.app.response.entity;

import vn.gteam.app.entity.AssetServer;
import lombok.Getter;
import lombok.Setter;
import vn.gteam.reflect.MyReflection;

@Getter
@Setter
@MyReflection
public class AssetServerResponseEntity {
    private String ip;
    private int port;

    public void fill(AssetServer assetServer){
        this.ip = assetServer.getIp();
        this.port = assetServer.getPort();
    }
}
