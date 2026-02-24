package vn.gteam.app.balance;

import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.manager.AssetServerManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetServerBalance {
    private final AssetServerManager assetServerManager;
    private int currentIndex;

    public AssetServerBalance(AssetServerManager assetServerManager){
        this.assetServerManager = assetServerManager;
    }

    public AssetServer getNextSlaveServer(){
        List<AssetServer> assetServerInfos = this.assetServerManager.getAssetServers();
        if (assetServerInfos.isEmpty()){
            return null;
        }
        AssetServer assetServerInfo = assetServerInfos.get(this.currentIndex);
        this.currentIndex = (this.currentIndex + 1) % assetServerInfos.size();
        return assetServerInfo;
    }
}
