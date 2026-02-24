package vn.gteam.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "game_servers")
public class GameServer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean online;
    private String message;
    private String ip;
    private Integer port;
    private Integer springPort;
    private Integer sorting;
}
