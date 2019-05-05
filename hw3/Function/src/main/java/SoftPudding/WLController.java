package SoftPudding;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*" ,maxAge = 3600)
@Controller    // This means that this class is a Controller
@RequestMapping(path="/wl") // This means URL's start with /wl
public class WLController{
    static private boolean isLoggedIn= false;

    static private WordLadder wl= new WordLadder();

    @CrossOrigin(origins = "*" ,maxAge = 3600)
    @GetMapping(path="/search")
    public @ResponseBody List<String> register(@RequestParam String w1,@RequestParam String w2)
    {
        if(isLoggedIn)
            return wl.search(w1,w2);
        else
            return Arrays.asList("You Hava Not Logged In!");
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(path="/login")
    public @ResponseBody boolean login(@RequestParam String name,@RequestParam String pwd)
    {
        String url = "http://authhost:9002/auth?name="+name+"&pwd="+pwd;
        HttpHeaders headers= new HttpHeaders();
        RestTemplate restTemplate= new RestTemplate();
        Boolean res =restTemplate.getForObject(url,Boolean.class);
        if(res)
        {
            isLoggedIn= true;
            return true;
        }
        else return false;
    }

}