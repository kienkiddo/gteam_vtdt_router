package vn.gteam.app.controller.client;

import com.google.gson.Gson;
import vn.gteam.app.factory.AppInfoServiceFactory;
import vn.gteam.app.properties.ConfigureAppProperties;
import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.service.appinfo.AppInfoDefaultService;
import vn.gteam.app.service.appinfo.AppInfoService;
import vn.gteam.lib.commom.Debug;
import vn.gteam.lib.crypt.AESCrypt;
import vn.gteam.lib.crypt.KdoCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/app")
public class ClientGetAppInfoController {
    private final ConfigureAppProperties configureAppProperties;
    private final AppInfoServiceFactory appInfoServiceFactory;
    private final Gson gson;

    public ClientGetAppInfoController(ConfigureAppProperties configureAppProperties, AppInfoServiceFactory appInfoServiceFactory){
        this.configureAppProperties = configureAppProperties;
        this.appInfoServiceFactory = appInfoServiceFactory;
        this.gson = new Gson();
    }

    @GetMapping("/info")
    public String getAppInfo(@RequestParam String hash, @RequestParam String version, @RequestParam Integer build, @RequestParam Integer platformType){
        AppInfoService appInfoService = this.appInfoServiceFactory.make(version, build, platformType);
        Debug.log("client type = " + platformType + " ; version = " + version + " ; build = " + build + " ---> service = " + appInfoService.getClass().getSimpleName());
        BaseResponse baseResponse = appInfoService.perform(version, build, platformType);
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
