package eoj3.hypercube;

import eoj3.hypercube.services.AnotherService;
import eoj3.hypercube.services.TaskExecutorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskExecutorTests {

    @Autowired
    AnotherService anotherService;

    @Autowired
    TaskExecutorService taskExecutorService;

    @Test
    public void testExampleService() {
        taskExecutorService.printMessages();
        System.out.println("test done");
    }
}
