package vn.gteam.app.response.message.client;

import vn.gteam.app.model.Platform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAppUpgradeResponse extends AppInfoResponse {
    private Platform platform;

    public GetAppUpgradeResponse() {
        super(UPGRADE);
    }
}
