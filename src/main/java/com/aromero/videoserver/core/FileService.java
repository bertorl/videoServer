package com.aromero.videoserver.core;

import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

public interface FileService {

    public Mono<Resource> getFileAsyncFromFileSystem(String uri);
}
