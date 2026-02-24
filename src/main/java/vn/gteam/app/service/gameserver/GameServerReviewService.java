package vn.gteam.app.service.gameserver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vn.gteam.app.config.AppReviewConfig;
import vn.gteam.app.config.ConfigData;
import vn.gteam.app.entity.GameServer;
import vn.gteam.app.response.message.client.GetListGameServerResponse;

import java.util.List;

@Component
@AllArgsConstructor
public class GameServerReviewService implements GameServerService{
    private final ConfigData configData;
    @Override
    public GetListGameServerResponse getListGameServer() {
        AppReviewConfig config = this.configData.getAppReviewConfig();
        List<GameServer> gameServers = List.of(config.getGameServer());

        GetListGameServerResponse getListGameServerResponse = new GetListGameServerResponse();
        getListGameServerResponse.fill(gameServers);
        getListGameServerResponse.setSuccess();
        return getListGameServerResponse;
    }
}
