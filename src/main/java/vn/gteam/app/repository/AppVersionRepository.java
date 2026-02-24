package vn.gteam.app.repository;

import vn.gteam.app.entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionRepository extends JpaRepository<AppVersion, Integer> {
}
