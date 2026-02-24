package vn.gteam.app.repository;

import vn.gteam.app.entity.GameServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameServerRepository extends JpaRepository<GameServer, Integer> {
}
