package com.company;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtility {
    public static void writeToFile(List<Car> cars) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        File file = new File("src/main/resources/cars.json");

        try {
            writer.writeValue(file, cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Car> readFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/cars.json")));
            return Arrays.asList(mapper.readValue(json, Car[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
