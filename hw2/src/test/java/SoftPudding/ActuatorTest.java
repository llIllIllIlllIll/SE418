package SoftPudding;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActuatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="user",roles={"USER"})
    public void normalResulrOfDogAndCat() throws Exception {

        this.mockMvc.perform(get("/wl/search?w1=cat&w2=dog")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[\"cat\",\"cot\",\"dot\",\"dog\"]"));
    }

    @Test
    @WithMockUser(username="user",roles={"USER"})
    public void wordNotInDictionary() throws Exception {

        this.mockMvc.perform(get("/wl/search?w1=softpudding&w2=softpudding")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[\"softpudding or softpudding not in dictionary.txt\"]" ));
    }


    @Test
    @WithMockUser(username="user",roles={"USER"})
    public void noPath() throws Exception {

        this.mockMvc.perform(get("/wl/search?w1=test&w2=love")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[\"Can not find a proper path.\"]" ));
    }
}
