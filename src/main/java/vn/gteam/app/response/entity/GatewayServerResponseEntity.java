package vn.gteam.app.response.entity;

import vn.gteam.app.entity.GatewayServer;
import lombok.Getter;
import lombok.Setter;
import vn.gteam.reflect.MyReflection;

@Getter
@Setter
@MyReflection
public class GatewayServerResponseEntity {
    private String url;

    public void fill(GatewayServer gatewayServer){
        this.url = gatewayServer.getUrl();
    }
}
