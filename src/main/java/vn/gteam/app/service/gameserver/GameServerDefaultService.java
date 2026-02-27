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

    public GetListGameServerResponse getListInternalGameServer(){
        List<GameServer> gameServers = this.gameServerManager.getGameServers();
        List<GameServer> internalGameServers = gameServers.stream()
                .map(g -> {
                    GameServer internal = new GameServer();
                    internal.setId(g.getId());
                    internal.setName(g.getName());
                    internal.setOnline(g.isOnline());
                    internal.setIp(g.getInternalIp());
                    internal.setPort(g.getPort());
                    internal.setSpringPort(g.getSpringPort());
                    internal.setSorting(g.getSorting());
                    return internal;
                }).toList();

        GetListGameServerResponse getListGameServerResponse = new GetListGameServerResponse();
        getListGameServerResponse.fill(internalGameServers);
        getListGameServerResponse.setSuccess();
        return getListGameServerResponse;
    }

    public BaseResponse getDetailGameServer(int serverId){
        GameServer gameServer = this.gameServerManager.findById(serverId);
        GatewayServer gatewayServer = this.gatewayServerManager.getFirst();

        GatewayServer internalGatewayServer = new GatewayServer();
        internalGatewayServer.setId(gatewayServer.getId());
        internalGatewayServer.setName(gatewayServer.getName());
        internalGatewayServer.setUrl(gatewayServer.getInternalUrl());

        GetDetailGameServerResponse response = new GetDetailGameServerResponse();
        response.fill(gameServer, internalGatewayServer);
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
