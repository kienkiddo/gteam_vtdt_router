package vn.gteam.app.controller.api;

import vn.gteam.app.properties.ConfigureAppProperties;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.service.AppVersionService;
import vn.gteam.app.service.GameServerService;
import vn.gteam.lib.commom.Debug;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class APIController {
    private final ConfigureAppProperties configureAppProperties;
    private final AppVersionService appVersionService;
    private final GameServerService gameServerService;

    public APIController(ConfigureAppProperties configureAppProperties, AppVersionService appVersionService, GameServerService gameServerService){
        this.configureAppProperties = configureAppProperties;
        this.appVersionService = appVersionService;
        this.gameServerService = gameServerService;
    }

    @GetMapping("/new-asset-version")
    public BaseResponse newAssetVersion(@RequestParam String secret){
        if (!Objects.equals(secret, this.configureAppProperties.getCoreGameServerSecret())){
            Debug.log("secret wrong ; value = " + secret);
            return null;
        }
        return this.appVersionService.newAssetVersion();
    }

    @GetMapping("/game-server/all")
    public BaseResponse getAllGameServer(@RequestParam String secret){
        if (!Objects.equals(secret, this.configureAppProperties.getCoreGameServerSecret())){
            Debug.log("secret wrong ; value = " + secret);
            return null;
        }
        return this.gameServerService.getListGameServer();
    }

    @GetMapping("/game-server/detail")
    public BaseResponse getDetailGameServer(@RequestParam String secret, @RequestParam Integer serverId){
        if (!Objects.equals(secret, this.configureAppProperties.getCoreGameServerSecret())){
            Debug.log("secret wrong ; value = " + secret);
            return null;
        }
        return this.gameServerService.getDetailGameServer(serverId);
    }


    @GetMapping("/game-server/update")
    public BaseResponse updateGameServer(@RequestParam String secret, @RequestParam Integer serverId, @RequestParam Integer status, @RequestParam String message){
        if (!Objects.equals(secret, this.configureAppProperties.getCoreGameServerSecret())){
            Debug.log("secret wrong ; value = " + secret);
            return null;
        }
        return this.gameServerService.updateStatusGameServer(serverId, status, message);
    }
}
