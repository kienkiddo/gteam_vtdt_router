package vn.gteam.app.controller.client;

import com.google.gson.Gson;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import jakarta.servlet.http.HttpServletRequest;
import vn.gteam.app.properties.ConfigureAppProperties;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.service.AppInfoService;
import vn.gteam.lib.IpUtils;
import vn.gteam.lib.commom.Debug;
import vn.gteam.lib.crypt.AESCrypt;
import vn.gteam.lib.crypt.KdoCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/client/app")
public class ClientGetAppInfoController {
    private final AppInfoService appInfoService;
    private final ConfigureAppProperties configureAppProperties;
    private final Gson gson;

    public ClientGetAppInfoController(AppInfoService appInfoService, ConfigureAppProperties configureAppProperties){
        this.appInfoService = appInfoService;
        this.configureAppProperties = configureAppProperties;
        this.gson = new Gson();
    }

    @GetMapping("/info")
    public String getAppInfo(@RequestParam String hash, @RequestParam String version, @RequestParam Integer build, @RequestParam Integer platformType){
        BaseResponse baseResponse = this.appInfoService.perform(version, build, platformType);
        String rawJson = this.gson.toJson(baseResponse);
        String rawText = "";
        try {
            String secret = hash + configureAppProperties.getClientAssetServerSecret();
            rawText = AESCrypt.encrypt(rawJson, secret);
            rawText = KdoCrypt.encrypt(rawText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Debug.log("hash = " + hash + " ; value = " + rawText);
        return rawText;
    }

}
