import com.liukhtenko.multithreading.creatorvan.VanCreator;
import com.liukhtenko.multithreading.entity.CustomTimer;
import com.liukhtenko.multithreading.entity.Van;
import com.liukhtenko.multithreading.exception.CustomException;
import com.liukhtenko.multithreading.reader.DataReader;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogisticBaseMain {
    public static void main(String[] args) throws CustomException {
        DataReader dataReader = new DataReader();
        VanCreator creator = new VanCreator();
        List<String> list = dataReader.read();
        List<Van> vans = creator.create(list);
        TimerTask timerTask = new CustomTimer();
        Timer timer = new Timer(true);
        timer.schedule(timerTask, 10, 1000);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (Van van : vans) {
            service.execute(van);
        }
        service.shutdown();
    }
}
