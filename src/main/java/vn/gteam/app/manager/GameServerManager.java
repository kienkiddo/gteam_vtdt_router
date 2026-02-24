package vn.gteam.app.manager;

import vn.gteam.app.entity.GameServer;
import vn.gteam.app.model.ILoader;
import vn.gteam.app.repository.GameServerRepository;
import vn.gteam.lib.commom.Debug;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameServerManager implements ILoader {
    private final GameServerRepository gameServerRepository;

    @Getter
    private List<GameServer> gameServers;
    private Map<Integer, GameServer> gameServersById;

    public GameServerManager(GameServerRepository gameServerRepository){
        this.gameServerRepository = gameServerRepository;
        this.gameServers = new LinkedList<>();
        this.gameServersById = new HashMap<>();
    }

    public GameServer findById(int serverId){
        return this.gameServersById.get(serverId);
    }

    @Override
    public void load() {
        this.gameServers.clear();
        this.gameServersById.clear();
        List<GameServer> entities = this.gameServerRepository.findAll();
        for (var entity : entities){
            this.gameServers.add(entity);
            this.gameServersById.put(entity.getId(), entity);
        }
        this.gameServers = this.gameServers.stream().sorted(Comparator.comparingInt(GameServer::getSorting).reversed()).toList();
        Debug.log(this, this.gameServers.size());
    }
}
