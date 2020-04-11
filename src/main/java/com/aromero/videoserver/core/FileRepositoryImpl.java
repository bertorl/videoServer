package com.aromero.videoserver.core;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Override
    public Resource findFileByID(String id) {
        return  new FileSystemResource(id);
    }

    @Override
    public void saveFile(String id, Resource resource) {

    }
}
