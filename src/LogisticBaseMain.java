import com.liukhtenko.multithreading.creatorvan.VanCreator;
import com.liukhtenko.multithreading.entity.CustomTimer;
import com.liukhtenko.multithreading.entity.LogisticBase;
import com.liukhtenko.multithreading.entity.Van;
import com.liukhtenko.multithreading.entity.Watcher;
import com.liukhtenko.multithreading.exception.CustomException;
import com.liukhtenko.multithreading.reader.DataReader;

import sun.rmi.runtime.NewThreadAction;

import java.util.*;
import java.util.concurrent.*;

public class LogisticBaseMain {
    public static void main(String[] args) throws CustomException, InterruptedException {
//        Van van1 = new Van("v1", true, false);
//        Van van2 = new Van("v2", false, true);
//        Van van3 = new Van("v3", false, false);
//        Van van4 = new Van("v4", false, false);
//        Van van5 = new Van("v5", true, true);
//        Van van6 = new Van("v6", false, false);    // FIXME: 16.01.2020 насчет конструктора Van
//        Van van7 = new Van("v7", true, true);
//        Van van8 = new Van("v8", false, true);
//        Van van9 = new Van("v9", false, false);
//        Van van10 = new Van("v10", false, true);
//        List<Van> vans = new ArrayList<>();
//        vans.add(van1);
//        vans.add(van2);
//        vans.add(van3);
//        vans.add(van4);
//        vans.add(van5);
//        vans.add(van6);
//        vans.add(van7);
//        vans.add(van8);
//        vans.add(van9);
//        vans.add(van10);
//        for (Van van : vans) {
//            van.start();
//        }

        DataReader dataReader = new DataReader();
        VanCreator creator = new VanCreator();
        List<String> list = dataReader.read();
        List<Van> vans = creator.create(list);

        TimerTask timerTask = new CustomTimer();
        Timer timer = new Timer(true);
        timer.schedule(timerTask,10,1000);

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (Van van : vans) {
                service.execute(van);
           // van.start();
        }
        service.shutdown();

    }
}
