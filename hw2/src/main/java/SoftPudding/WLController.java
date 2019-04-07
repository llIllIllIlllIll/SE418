package SoftPudding;

import jdk.internal.jline.internal.TestAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import java.util.List;

@CrossOrigin(origins = "*" ,maxAge = 3600)
@Controller    // This means that this class is a Controller
@RequestMapping(path="/wl") // This means URL's start with /wl
public class WLController{

    private WordLadder wl= new WordLadder();

    @CrossOrigin(origins = "*" ,maxAge = 3600)
    @GetMapping(path="/search")
    public @ResponseBody List<String> register(@RequestParam String w1,@RequestParam String w2)
    {
        return wl.search(w1,w2);
    }

}