package vn.gteam.app.startup;

import vn.gteam.app.manager.GameServerManager;
import vn.gteam.app.model.ILoader;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import vn.gteam.app.manager.AppVersionManager;
import vn.gteam.app.manager.AssetServerManager;
import vn.gteam.app.manager.GatewayServerManager;

@Component
public class AppStartup {
    private final ApplicationContext context;
    private final DatabaseStartup databaseStartup;
    private final Class<?>[] managerClasses;

    public AppStartup(ApplicationContext applicationContext, DatabaseStartup databaseStartup){
        this.context = applicationContext;
        this.databaseStartup = databaseStartup;

        this.managerClasses = new Class<?>[]{
                AppVersionManager.class,
                GameServerManager.class,
                AssetServerManager.class,
                GatewayServerManager.class
        };
    }

    public void init(){
        this.databaseStartup.init();
        this.load();
    }

    public void load(){
        for (Class<?> managerClass : this.managerClasses){
            ILoader loader = (ILoader) this.context.getBean(managerClass);
            loader.load();
        }
    }
}
