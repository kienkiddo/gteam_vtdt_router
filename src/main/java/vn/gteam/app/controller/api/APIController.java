package vn.gteam.app.controller.api;

import vn.gteam.app.properties.ConfigureAppProperties;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.service.common.AppVersionService;
import vn.gteam.app.service.gameserver.GameServerDefaultService;
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
    private final GameServerDefaultService gameServerDefaultService;

    public APIController(ConfigureAppProperties configureAppProperties, AppVersionService appVersionService, GameServerDefaultService gameServerDefaultService){
        this.configureAppProperties = configureAppProperties;
        this.appVersionService = appVersionService;
        this.gameServerDefaultService = gameServerDefaultService;
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
        return this.gameServerDefaultService.getListInternalGameServer();
    }

    @GetMapping("/game-server/detail")
    public BaseResponse getDetailGameServer(@RequestParam String secret, @RequestParam Integer serverId){
        if (!Objects.equals(secret, this.configureAppProperties.getCoreGameServerSecret())){
            Debug.log("secret wrong ; value = " + secret);
            return null;
        }
        return this.gameServerDefaultService.getDetailGameServer(serverId);
    }


    @GetMapping("/game-server/update")
    public BaseResponse updateGameServer(@RequestParam String secret, @RequestParam Integer serverId, @RequestParam Integer status, @RequestParam String message){
        if (!Objects.equals(secret, this.configureAppProperties.getCoreGameServerSecret())){
            Debug.log("secret wrong ; value = " + secret);
            return null;
        }
        return this.gameServerDefaultService.updateStatusGameServer(serverId, status, message);
    }
}
