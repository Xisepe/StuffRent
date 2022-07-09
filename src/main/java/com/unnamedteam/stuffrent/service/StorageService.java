package com.unnamedteam.stuffrent.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.nio.file.Path;

public interface StorageService {
    void init();
    String store(MultipartFile file);
    Path load(String filename);
    Resource loadAsResource(String filename) throws FileNotFoundException;
}
