package eoj3.hypercube.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CrossRun {
    public CrossRun() {
    }

    private static java.util.List<String> messages = java.util.Collections.synchronizedList(new java.util.ArrayList());
    private static volatile boolean failed = false;

    private static void error(String paramString) {
        System.out.println("ERROR: " + paramString);
        System.exit(1);
    }

    public static void main(String[] paramArrayOfString) {
        if (paramArrayOfString.length != 2) {
            error("Expected exactly two arguments: the first process command line and the second process command line.");
        }

        long l = System.currentTimeMillis();
        try {
            runProcesses(paramArrayOfString);
        } catch (IOException localIOException) {
            error(localIOException.getMessage());
        }

        System.out.println("Completed in " + (System.currentTimeMillis() - l) + " ms.");
    }

    private static void runProcesses(String[] paramArrayOfString) throws IOException {
        Process localProcess1 = new ProcessBuilder(paramArrayOfString[0].split(" ")).start();
        Process localProcess2 = new ProcessBuilder(paramArrayOfString[1].split(" ")).start();

        Thread localThread1 = new Thread(new CrossRun.StreamProxyRunner("first", "second", localProcess1.getInputStream(), localProcess2.getOutputStream()));

        Thread localThread2 = new Thread(new CrossRun.StreamProxyRunner("second", "first", localProcess2.getInputStream(), localProcess1.getOutputStream()));


        localThread1.start();
        localThread2.start();

        int i = -1;
        try {
            i = localProcess1.waitFor();
        } catch (InterruptedException localInterruptedException1) {
            error(localInterruptedException1.getMessage());
        }

        int j = -1;
        try {
            j = localProcess2.waitFor();
        } catch (InterruptedException localInterruptedException2) {
            error(localInterruptedException2.getMessage());
        }
        try {
            localThread1.join();
        } catch (InterruptedException localInterruptedException3) {
            error(localInterruptedException3.getMessage());
        }
        try {
            localThread2.join();
        } catch (InterruptedException localInterruptedException4) {
            error(localInterruptedException4.getMessage());
        }

        if (i != 0) {
            messages.add("The first process returned with exit code " + i + ".");
        }

        if (j != 0) {
            messages.add("The second process returned with exit code " + j + ".");
        }

        for (String str : messages) {
            System.out.println("* " + str);
        }

        if (failed) {
            System.exit(1);
        }
    }

    private static final class StreamProxyRunner implements Runnable {
        private final String firstProcessName;
        private final String secondProcessName;
        private final InputStream inputStream;
        private final OutputStream outputStream;

        private StreamProxyRunner(String paramString1, String paramString2, InputStream paramInputStream, OutputStream paramOutputStream) {
            firstProcessName = paramString1;
            secondProcessName = paramString2;
            inputStream = paramInputStream;
            outputStream = paramOutputStream;
        }

        public void run() {
            byte[] arrayOfByte = new byte[65536];
            for (; ; ) {
                int i;
                try {
                    i = inputStream.read(arrayOfByte);
                } catch (IOException localIOException3) {
                    CrossRun.messages.add("Unexpected exception " + localIOException3.getClass().getSimpleName() + " while reading from the output of the " + firstProcessName + " process: " + localIOException3.getMessage());
                    CrossRun.failed = true;
                    break;
                }

                if (i < 0) {
                    break;
                }
                try {
                    outputStream.write(arrayOfByte, 0, i);
                    outputStream.flush();
                } catch (IOException localIOException4) {
                    CrossRun.messages.add("Unexpected exception " + localIOException4.getClass().getSimpleName() + " while writing to the input of the " + secondProcessName + " process: " + localIOException4.getMessage());
                    CrossRun.failed = true;
                    break;
                }
            }
            try {
                inputStream.close();
            } catch (IOException localIOException1) {
            }

            try {
                outputStream.close();
            } catch (IOException localIOException2) {
            }
        }
    }
}