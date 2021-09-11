package org.example.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WriteToCsv {

    private String split = ",";
    private String fileName;

    public WriteToCsv(String filename) {
        this.fileName = filename;
        Path path = Paths.get(fileName);

        try {
            boolean exists = Files.exists(path);
            if (!exists) {
                String fileDirectory = fileName.substring(0,fileName.lastIndexOf("/"));
                Files.createDirectories(Paths.get(fileDirectory));
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAll(List<String[]> datarow) {

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < datarow.size(); i++) {
                for (int j = 0; j < datarow.get(i).length; j++) {
                    wr.write(datarow.get(i)[j]);
                    if (j != datarow.get(i).length - 1)
                        wr.write(split);
                }
                if (i != datarow.size() - 1)
                    wr.write("\n");
            }
            wr.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}