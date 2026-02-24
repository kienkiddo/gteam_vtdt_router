package vn.gteam.app.controller.client;

import com.google.gson.Gson;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import jakarta.servlet.http.HttpServletRequest;
import vn.gteam.app.properties.ConfigureAppProperties;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.response.entity.GameServerResponseEntity;
import vn.gteam.app.response.message.client.GetListGameServerResponse;
import vn.gteam.app.service.GameServerService;
import vn.gteam.lib.IpUtils;
import vn.gteam.lib.commom.Debug;
import vn.gteam.lib.crypt.AESCrypt;
import vn.gteam.lib.crypt.KdoCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@RestController
@RequestMapping("client/game-server")
public class ClientGetListGameServerController {
    private final ConfigureAppProperties configureAppProperties;
    private final GameServerService gameServerService;
    private final Gson gson;

    public ClientGetListGameServerController(ConfigureAppProperties configureAppProperties, GameServerService gameServerService){
        this.configureAppProperties = configureAppProperties;
        this.gameServerService = gameServerService;
        this.gson = new Gson();
    }

    @GetMapping("/info")
    public String getListGameServer(HttpServletRequest request){
        GetListGameServerResponse baseResponse = this.gameServerService.getListGameServer();
       // this.updateConvert(baseResponse, request);
        String rawJson = this.gson.toJson(baseResponse);
        String rawText = "";
        try {
            rawText = AESCrypt.encrypt(rawJson, configureAppProperties.getClientGameServerSecret());
            rawText = KdoCrypt.encrypt(rawText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawText;
    }

    private GetListGameServerResponse updateConvert(GetListGameServerResponse response, HttpServletRequest request){
        try {
            if (!this.isVietNamIP(request)){
                for (GameServerResponseEntity entity : response.getGameServers()){
                    entity.setIp("sv1.dhtvn.tpl.vn");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private boolean isVietNamIP(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String clientIp = IpUtils.getClientIp(request);
        File database = new File("resources/GeoLite2-Country.mmdb");
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        InetAddress ipAddress = InetAddress.getByName(clientIp);
        CountryResponse response = reader.country(ipAddress);
        if (response.getCountry().getIsoCode().equalsIgnoreCase("VN")){
            Debug.log("--> IP = " + clientIp + " ; detected = VN");
            return true;
        }
        Debug.log("IP = " + clientIp + " ; detected = " + response.getCountry().getIsoCode());
        return false;
    }

}
