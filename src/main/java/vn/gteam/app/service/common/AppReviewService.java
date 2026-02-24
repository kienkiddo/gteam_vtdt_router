package vn.gteam.app.service.common;

public interface AppReviewService {
    boolean match(String version, int build, int platformType);
}
