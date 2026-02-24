package vn.gteam.app.service.gameserver;

import vn.gteam.app.entity.GameServer;
import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.manager.GameServerManager;
import vn.gteam.app.manager.GatewayServerManager;
import vn.gteam.app.repository.GameServerRepository;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.response.message.client.GetListGameServerResponse;
import vn.gteam.app.response.message.server.GetDetailGameServerResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameServerDefaultService implements GameServerService {
    private final GameServerManager gameServerManager;
    private final GatewayServerManager gatewayServerManager;

    private final GameServerRepository gameServerRepository;

    public GameServerDefaultService(GameServerManager gameServerManager, GatewayServerManager gatewayServerManager, GameServerRepository gameServerRepository){
        this.gameServerManager = gameServerManager;
        this.gatewayServerManager = gatewayServerManager;
        this.gameServerRepository = gameServerRepository;
    }

    @Override
    public GetListGameServerResponse getListGameServer(){
        List<GameServer> gameServers = this.gameServerManager.getGameServers();
        GetListGameServerResponse getListGameServerResponse = new GetListGameServerResponse();
        getListGameServerResponse.fill(gameServers);
        getListGameServerResponse.setSuccess();
        return getListGameServerResponse;
    }

    public BaseResponse getDetailGameServer(int serverId){
        GameServer gameServer = this.gameServerManager.findById(serverId);
        GatewayServer gatewayServer = this.gatewayServerManager.getFirst();

        GetDetailGameServerResponse response = new GetDetailGameServerResponse();
        response.fill(gameServer, gatewayServer);
        return response.setSuccess();
    }

    public BaseResponse updateStatusGameServer(int serverId, int status, String message){
        GameServer gameServer = this.gameServerManager.findById(serverId);
        gameServer.setOnline(status == 1);
        gameServer.setMessage(message);
        this.gameServerRepository.save(gameServer);
        return (new BaseResponse()).setSuccess();
    }
}
