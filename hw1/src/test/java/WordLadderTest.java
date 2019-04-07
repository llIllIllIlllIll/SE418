import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordLadderTest {

    static WordLadder w;
    @Before
    public void prep(){
        w=new WordLadder();
    }

    @Test
    public void test1(){
        Assert.assertEquals(w.search("code","date"),4);
    }
    @Test
    public void test2(){
        Assert.assertEquals(w.search("dog","cat"),4);
    }
}