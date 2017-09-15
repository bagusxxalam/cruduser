package net.blueapple.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lithium on 9/15/2017.
 */
@RestController
public class HomeController {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String home() {
        return "Welcome";
    }
}
