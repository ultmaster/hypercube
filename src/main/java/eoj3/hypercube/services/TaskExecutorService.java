package eoj3.hypercube.services;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TaskExecutorService {

    private class MessagePrinterTask implements Runnable {

        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println(message);
        }
    }

    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    public TaskExecutorService(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void print(String s) {
        System.out.println(s);
    }

    @Async
    public void printMessages() {
        for (int i = 0; i < 25; i++) {
            print("Message" + i);
//            taskExecutor.execute(new MessagePrinterTask("Message " + i));
        }
        System.out.println("done");
    }
}