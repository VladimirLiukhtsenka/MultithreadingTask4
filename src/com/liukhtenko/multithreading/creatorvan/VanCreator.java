package com.liukhtenko.multithreading.creatorvan;

import com.liukhtenko.multithreading.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class VanCreator {
    private final static String SPACE = "\\s";
    static Logger logger = LogManager.getLogger();

    public  List<Van> create(List<String> list) {
        List<Van> vans = new ArrayList<>();
        for (String line : list) {
            Van van = createFromLine(line);
          //  logger.log(Level.DEBUG, "Create new van: " + van);
            vans.add(van);
        }
        return vans;
    }

    private Van createFromLine(String line) {
        String[] pos = line.split(SPACE);
        Van van = new Van();
        van.setVanName(pos[0]);
        van.setLoaded(Boolean.parseBoolean(pos[1]));
        van.setPerishableGoods(Boolean.parseBoolean(pos[2]));
        return van;
    }
}
