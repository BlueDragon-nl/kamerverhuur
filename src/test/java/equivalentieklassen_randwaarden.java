import main.kamerverhuur.Controllers.SpeelbordController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class equivalentieklassen_randwaarden {
    @Test
    void randvoorwaarden(){
        SpeelbordController s = new SpeelbordController();
        Assertions.assertEquals(s.factor(0), -1);
        Assertions.assertEquals(s.factor(1), 500);
        Assertions.assertEquals(s.factor(5), 100);
        Assertions.assertEquals(s.factor(9), 55);
        Assertions.assertEquals(s.factor(10), 75);
        Assertions.assertEquals(s.factor(11), 68);
        Assertions.assertEquals(s.factor(19), 39);
        Assertions.assertEquals(s.factor(20), 50);
        Assertions.assertEquals(s.factor(25), 40);
    }
}
