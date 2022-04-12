import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRNB {

    @Test
    public void testTest(){

        int a[] = { 16, 3, 7, 11, 9, 26, 18, 14, 15 };

        RedNBlack<Integer, Integer> t = new RedNBlack<>();

        for (int i = 0; i < a.length; i++) {

            t.insert(a[i], a[i]);

        }

        Assertions.assertEquals(t.test(t.getMain()), "379111415161826");

    }

}

