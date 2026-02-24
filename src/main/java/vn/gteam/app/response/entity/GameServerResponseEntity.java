package vn.gteam.app.response.entity;

import vn.gteam.app.entity.GameServer;
import lombok.Getter;
import lombok.Setter;
import vn.gteam.reflect.MyReflection;

@Getter
@Setter
@MyReflection
public class GameServerResponseEntity {
    private int id;
    private String name;
    private boolean online;
    private String message;
    private String ip;
    private Integer port;
    private Integer springPort;
    private Integer sorting;

    public void fill(GameServer gameServer){
        this.id = gameServer.getId();
        this.name = gameServer.getName();
        this.ip = gameServer.getIp();
        this.port = gameServer.getPort();
        this.online = gameServer.isOnline();
        this.message = gameServer.getMessage();
        this.springPort = gameServer.getSpringPort();
        this.sorting = gameServer.getSorting();
    }
}
