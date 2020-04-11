package com.aromero.videoserver.core;

import org.springframework.core.io.Resource;

public interface FileRepository {

    public Resource findFileByID(String id);
    public void saveFile(String id, Resource resource);

}
