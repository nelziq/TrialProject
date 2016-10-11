import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class WeeblyParallelTest {

    @Test
    public void parallelTest() {
        Class[] cls = {WeeblyTest.class};
        JUnitCore.runClasses(ParallelComputer.methods(), cls);
    }

}
