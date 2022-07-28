import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Main {
    public static int countFinishedFutures(List<Future> futures) {
        return (int) futures.stream().filter(object -> object.isDone()).count();
    }

public static Callable<Double> getCallable() {
    return new Callable<Double>() {
        @Override
        public Double call() throws Exception {
            return Math.random();
        }
    };
}
    public static void main (String[] args){
        final int FUTURE_OBJECTS = 7;
        List<Future> futureList = new ArrayList<Future>();
        ExecutorService executor = Executors.newFixedThreadPool(FUTURE_OBJECTS);
        IntStream.rangeClosed(1, FUTURE_OBJECTS).forEach((i) -> {
            futureList.add(executor.submit(getCallable()));
        });
        System.out.println(countFinishedFutures(futureList));
        executor.shutdown();
    }
}