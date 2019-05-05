package Softpudding.SimpleAuth;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleAuth {
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/auth")
    public boolean auth(@RequestParam String name, @RequestParam String pwd) {
        if(name.equals("softpudding")&&pwd.equals("123456"))
            return true;
        else return false;
    }
}
