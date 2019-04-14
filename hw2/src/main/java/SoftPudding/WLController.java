package SoftPudding;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*" ,maxAge = 3600)
@Controller    // This means that this class is a Controller
@RequestMapping(path="/wl") // This means URL's start with /wl
public class WLController{

    static private WordLadder wl= new WordLadder();

    @CrossOrigin(origins = "*" ,maxAge = 3600)
    @GetMapping(path="/search")
    public @ResponseBody List<String> register(@RequestParam String w1,@RequestParam String w2)
    {
        return wl.search(w1,w2);
    }

}