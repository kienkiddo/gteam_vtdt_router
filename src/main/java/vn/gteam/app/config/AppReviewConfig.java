package vn.gteam.app.config;

import lombok.Getter;
import lombok.Setter;
import vn.gteam.app.entity.AssetServer;
import vn.gteam.app.entity.GameServer;
import vn.gteam.app.entity.GatewayServer;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class AppReviewConfig {
    private Map<Integer, Set<String>> matches; // by platform type -> list version
    private AssetServer assetServer;
    private GatewayServer gatewayServer;
    private GameServer gameServer;
}
