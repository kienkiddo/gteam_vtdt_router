package vn.gteam.app.factory;

import vn.gteam.app.service.appinfo.AppInfoService;

public interface AppInfoServiceFactory {
    AppInfoService make(String version, int build, int platformType);
}
