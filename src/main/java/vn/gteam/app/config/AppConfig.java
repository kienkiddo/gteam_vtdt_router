package vn.gteam.app.config;

import vn.gteam.app.model.Platform;
import vn.gteam.app.model.PlatformType;
import lombok.Getter;
import lombok.Setter;
import vn.gteam.reflect.MyReflection;

@Getter
@Setter
@MyReflection
public class AppConfig {
    private Platform windows;
    private Platform android;
    private Platform ios;

    public Platform getByPlatformType(int type){
        switch (type){
            case PlatformType.WINDOWS:
                return this.windows;
            case PlatformType.ANDROID:
                return this.android;
            case PlatformType.IOS:
                return this.ios;
        }
        return null;
    }
}
