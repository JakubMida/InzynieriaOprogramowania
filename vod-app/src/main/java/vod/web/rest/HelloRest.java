package vod.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloRest {

    @RequestMapping(value = "/hello")
    String sayHello() {return "Hey Universe!";}
}
