package vn.gteam.app.controller;


import vn.gteam.app.properties.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    private MyProperties myProperties;

    @GetMapping("/")
    public String index(){
        System.out.println("port = " + this.myProperties.getServerPort());
        return "Hello world !";
    }
}
