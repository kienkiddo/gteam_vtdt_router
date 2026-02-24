package vn.gteam.app.controller.admin;


import vn.gteam.app.response.BaseResponse;
import vn.gteam.app.startup.AppStartup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AppStartup appStartup;

    public AdminController(AppStartup appStartup){
        this.appStartup = appStartup;
    }

    @GetMapping("/reload")
    public BaseResponse reload(){
        this.appStartup.init();
        return (new BaseResponse()).setSuccess();
    }
}
