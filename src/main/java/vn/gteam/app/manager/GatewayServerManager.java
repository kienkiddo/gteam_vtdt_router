package vn.gteam.app.manager;

import vn.gteam.app.entity.GatewayServer;
import vn.gteam.app.model.ILoader;
import vn.gteam.app.repository.GatewayServerRepository;
import vn.gteam.lib.commom.Debug;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GatewayServerManager implements ILoader {
    private final GatewayServerRepository gatewayServerRepository;

    @Getter
    private List<GatewayServer> gatewayServers;

    public GatewayServerManager(GatewayServerRepository gatewayServerRepository){
        this.gatewayServerRepository = gatewayServerRepository;
        this.gatewayServers = new LinkedList<>();
    }

    public GatewayServer getFirst(){
        return this.gatewayServers.get(0);
    }

    @Override
    public void load() {
        this.gatewayServers.clear();
        this.gatewayServers = this.gatewayServerRepository.findAll();
        Debug.log(this, this.gatewayServers.size());
    }
}
