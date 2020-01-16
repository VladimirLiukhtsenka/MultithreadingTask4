package com.liukhtenko.multithreading.reader;

import com.liukhtenko.multithreading.exception.CustomException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReader {
    private final static String DEFAULT_PATH = "data/data.txt";

    public DataReader() {
    }

    public List<String> read() throws CustomException {
        List<String> list;
        try (Stream<String> stream = Files.lines(Paths.get(DEFAULT_PATH))) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new CustomException("File cannot be read");
        }
        return list;
    }
}
