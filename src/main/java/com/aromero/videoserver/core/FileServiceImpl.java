package com.aromero.videoserver.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FileServiceImpl implements FileService {

    public static final String BEAN_NAME = "FileService";

    private final FileRepository fileRepository;
    // private fina

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public Mono<Resource> getFileAsyncFromFileSystem(String uri) {
        Resource file = this.fileRepository.findFileByID(uri);
        return Mono.just(file);
    }
}
