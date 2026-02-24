package vn.gteam.app.model;

import lombok.Getter;
import lombok.Setter;
import vn.gteam.reflect.MyReflection;

@Getter
@Setter
@MyReflection
public class Platform {
    private String version;
    private int build;
    private String url;
}
