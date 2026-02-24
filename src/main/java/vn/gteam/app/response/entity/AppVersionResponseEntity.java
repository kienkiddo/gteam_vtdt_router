package vn.gteam.app.response.entity;

import vn.gteam.app.entity.AppVersion;
import lombok.Getter;
import lombok.Setter;
import vn.gteam.reflect.MyReflection;

@Getter
@Setter
@MyReflection
public class AppVersionResponseEntity {
    private int assetVersion;

    public void fill(AppVersion appVersion){
        this.assetVersion = appVersion.getAssetVersion();
    }
}
