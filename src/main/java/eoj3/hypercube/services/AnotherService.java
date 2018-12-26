package eoj3.hypercube.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnotherService {
    private TaskExecutorService taskExecutorService;

    @Autowired
    public AnotherService(TaskExecutorService taskExecutorService) {
        this.taskExecutorService = taskExecutorService;
    }

    public void printMessages() {
        taskExecutorService.printMessages();
        System.out.println("test done");
    }
}
