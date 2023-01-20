package com.example.recipes.services.impl;

import com.example.recipes.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceIngImpl implements FilesService {

    @Value("${path.to.data.file.ing}")
    private String dataFilePath;

   @Value("${name.of.data.file.ing}")
   private String dataFileNameIng;

    @Override
    public boolean saveToFile(String json){
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileNameIng), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile(){
        try {
            return Files.readString(Path.of(dataFilePath, dataFileNameIng));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile(){
        try {
            Path path = Path.of(dataFilePath,dataFileNameIng);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFile(){
        return new File(dataFilePath + "/" + dataFileNameIng);
    }

    @Override
    public Path createTempFile(String suffix){
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
