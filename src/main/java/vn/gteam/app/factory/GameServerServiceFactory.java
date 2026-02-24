package vn.gteam.app.factory;

import vn.gteam.app.service.gameserver.GameServerService;

public interface GameServerServiceFactory {
    GameServerService make(String version, int build, int platformType);
}
