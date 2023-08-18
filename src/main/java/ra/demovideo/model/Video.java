package ra.demovideo.model;

public class Video {
    private Long id;
    private String videoUrl;
    private  String title;
    private  String description;

    public Video() {
    }

    public Video(Long id, String videoUrl, String title, String description) {
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
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
