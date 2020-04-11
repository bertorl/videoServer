package com.aromero.videoserver.services.video;

import com.aromero.videoserver.core.FileService;
import com.aromero.videoserver.services.video.service.VideoService;
import com.aromero.videoserver.services.video.service.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/video")
@CrossOrigin(origins = {"*", "http://localhost:4200"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD})
public class VideoController {

    private final FileService fileService;

    @Autowired
    public VideoController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public Resource getVideo(@PathVariable("id") String id) {
        //return this.fileService.getFileAsyncFromFileSystem(VideoServiceImpl.URI_DATA + id);
        return new FileSystemResource(VideoServiceImpl.URI_DATA + id);
    }

    @GetMapping(value = "/mpd", produces = "application/dash+xml")
    public Resource getVideoMpd(ServerHttpResponse response) {

        response.getHeaders().add("Content-Disposition","attachment; filename=\"test.mpd\"");
        return new FileSystemResource(VideoServiceImpl.URI_DATA + "test.mpd");
    }
}
