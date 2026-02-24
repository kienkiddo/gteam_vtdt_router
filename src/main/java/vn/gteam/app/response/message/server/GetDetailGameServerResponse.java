package vn.gteam.app.response.message.server;

import vn.gteam.app.entity.GameServer;
import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.response.entity.GameServerResponseEntity;
import vn.gteam.app.response.entity.GatewayServerResponseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDetailGameServerResponse extends BaseResponse {
    private GameServerResponseEntity gameServer;
    private GatewayServerResponseEntity gatewayServer;

    public void fill(GameServer gameServer, GatewayServer gatewayServer){
        this.gameServer = new GameServerResponseEntity();
        this.gameServer.fill(gameServer);

        this.gatewayServer = new GatewayServerResponseEntity();
        this.gatewayServer.fill(gatewayServer);
    }
}
