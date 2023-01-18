package com.example.recipes.services.impl;

import com.example.recipes.services.FilesService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesServiceRecImpl implements FilesService {
    @Value("${path.to.data.file.rec}")
    private String dataFilePath;

    @Value("${name.of.data.file.rec}")
    private String dataFileNameRec;

    @Override
    public boolean saveToFile(String json){
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileNameRec), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile(){
        try {
            return Files.readString(Path.of(dataFilePath, dataFileNameRec));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile(){
        try {
            Path path = Path.of(dataFilePath, dataFileNameRec);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}