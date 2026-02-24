package vn.gteam.app.manager;

import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.model.ILoader;
import vn.gteam.app.repository.AssetServerRepository;
import vn.gteam.lib.commom.Debug;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class AssetServerManager implements ILoader {
    private final AssetServerRepository assetServerRepository;
    @Getter
    private List<AssetServer> assetServers;

    public AssetServerManager(AssetServerRepository assetServerRepository){
        this.assetServerRepository = assetServerRepository;
        this.assetServers = new LinkedList<>();
    }

    @Override
    public void load() {
        this.assetServers = this.assetServerRepository.findAll();
        Debug.log(this, this.assetServers.size());
    }
}
