package vn.gteam.app.response.message.client;

import vn.gteam.app.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppInfoResponse extends BaseResponse {
    private int typeResponse;

    protected static final int GET_APP_INFO = 0;
    protected static final int UPGRADE = 1;
}
