import com.liukhtenko.multithreading.entity.LogisticBase;
import com.liukhtenko.multithreading.entity.Van;
import com.liukhtenko.multithreading.service.VanService;
import sun.rmi.runtime.NewThreadAction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LogisticBaseMain {
    public static void main(String[] args) {
        LogisticBase logisticBase = LogisticBase.getInstance();
        Van van1 = new Van("v1", false, false);
        Van van2 = new Van("v2", false, false);
        Van van3 = new Van("v3", false, false);
        Van van4 = new Van("v4", false, false);
        Van van5 = new Van("v5", false, false);
        Van van6 = new Van("v6", false, false);
        Van van7 = new Van("v7", false, false);
        Van van8 = new Van("v8", false, false);
        Van van9 = new Van("v9", false, false);
        Van van10 = new Van("v10", false, false);
        List<Van> vans = new ArrayList<>();
        vans.add(van1);
        vans.add(van2);
        vans.add(van3);
        vans.add(van4);
        vans.add(van5);
        vans.add(van6);
        vans.add(van7);
        vans.add(van8);
        vans.add(van9);
        vans.add(van10);
        for (Van van : vans) {
            VanService vanService = new VanService(van);
            vanService.start();
//            new Thread(()->logisticBase.startVan(van)).start();
//            try {
//                TimeUnit.MILLISECONDS.sleep(0);
//                System.out.println(van+" is service");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            logisticBase.finishVan(van);
        }
    }
}
