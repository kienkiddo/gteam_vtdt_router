package vn.gteam.app.service.appinfo;

import vn.gteam.app.response.BaseResponse;

public interface AppInfoService {
    BaseResponse perform(String version, int build, int platformType);
}
