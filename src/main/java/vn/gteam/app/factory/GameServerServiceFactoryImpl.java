package vn.gteam.app.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vn.gteam.app.service.common.AppReviewService;
import vn.gteam.app.service.gameserver.GameServerDefaultService;
import vn.gteam.app.service.gameserver.GameServerReviewService;
import vn.gteam.app.service.gameserver.GameServerService;

@Component
@AllArgsConstructor
public class GameServerServiceFactoryImpl implements GameServerServiceFactory{
    private final AppReviewService appReviewService;
    private final GameServerDefaultService gameServerDefaultService;
    private final GameServerReviewService gameServerReviewService;

    @Override
    public GameServerService make(String version, int build, int platformType) {
        if (this.appReviewService.match(version, build, platformType)) {
            return this.gameServerReviewService;
        }
        return this.gameServerDefaultService;
    }
}
