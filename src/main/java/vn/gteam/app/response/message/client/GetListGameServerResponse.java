package vn.gteam.app.response.message.client;

import vn.gteam.app.entity.GameServer;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.response.entity.GameServerResponseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class GetListGameServerResponse extends BaseResponse {
    private List<GameServerResponseEntity> gameServers;

    public GetListGameServerResponse(){
        this.gameServers = new LinkedList<>();
    }

    public void fill(List<GameServer> gameServers){
        for (GameServer gameServer : gameServers){
            GameServerResponseEntity entity = new GameServerResponseEntity();
            entity.fill(gameServer);
            this.gameServers.add(entity);
        }
    }
}
