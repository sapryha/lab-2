import org.omg.CORBA.TIMEOUT;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int ThreadsCount = 5;
    private static ExecutorService executorService = Executors.newFixedThreadPool(ThreadsCount);
    private static Call[] calls;
    private static Tower[] towers;

    public static void main(String[] args) throws InterruptedException {
        ReadData("input.bin");
        for (int i = 0; i < calls.length; i++) {
            executorService.execute(calls[i]);
        }
        executorService.shutdown();
        executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }

    private static void ReadData(String fileName) {
        try {
            DataInputStream stream = new DataInputStream(new FileInputStream(fileName));
            int towersCount = stream.readInt();
            towers = new Tower[towersCount];
            for (int i = 0; i < towersCount; i++) {
                towers[i] = new Tower(new Coordinates(stream.readInt(), stream.readInt()),
                        stream.readInt());
            }

            int callsCount = stream.readInt();
            calls = new Call[callsCount];
            for (int i = 0; i < callsCount; i++) {
                calls[i] = new Call(towers, new Coordinates(stream.readInt(), stream.readInt()));
            }
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
