package ra.demovideo.model;

import org.springframework.web.multipart.MultipartFile;

public class VideoDto {
    private Long id;
    private MultipartFile videoUrl;
    private  String title;
    private  String description;

    public VideoDto() {
    }

    public VideoDto(Long id, MultipartFile videoUrl, String title, String description) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(MultipartFile videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
