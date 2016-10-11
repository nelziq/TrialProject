import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class WeeblyParallelTest {

    @Test
    public void parallelTest() {
        Class[] cls = {SignUpTest.class, LogInTest.class};
        JUnitCore.runClasses(ParallelComputer.classes(), cls);
    }

}
