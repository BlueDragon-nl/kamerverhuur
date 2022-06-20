import main.kamerverhuur.Controllers.SpeelbordController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class equivalentieklassen_randwaarden {
    @Test
    void randvoorwaarden(){
        SpeelbordController s = new SpeelbordController();
        Assertions.assertEquals(s.temporyfield (0), -1);
        Assertions.assertEquals(s.temporyfield(1), 500);

        Assertions.assertEquals(s.temporyfield(49), 10);
        Assertions.assertEquals(s.temporyfield(50), 20);

        Assertions.assertEquals(s.temporyfield(99), 10);
        Assertions.assertEquals(s.temporyfield(100), 20);

    }
}
