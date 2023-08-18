package ra.demovideo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.demovideo.model.Video;
import ra.demovideo.model.VideoDto;
import ra.demovideo.service.impl.VideoService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
@PropertySource("classpath:upload.properties")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Value("${upload-path}")
    private String pathUpload;
    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("list",videoService.findAll());
        return "list";
    }
   @GetMapping("/upload")
   public ModelAndView upload() {
        return new ModelAndView("upload","video",new VideoDto());
    }
    @PostMapping("/upload")
    public  String doUpload(@ModelAttribute("video") VideoDto videoDto){
       // upload file
        File file =new File(pathUpload);
        if(!file.exists()){
            // chưa tồn tại folder , khởi tạo 1 folder mới
            file.mkdirs();
        }
        String fileName = videoDto.getVideoUrl().getOriginalFilename();
        try {
            FileCopyUtils.copy(videoDto.getVideoUrl().getBytes(),new File(pathUpload+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // chuyen doi thanh doi tuong video
        Video newVideo= new Video();
        newVideo.setVideoUrl(fileName);
        newVideo.setTitle(videoDto.getTitle());
        newVideo.setDescription(videoDto.getDescription());
        videoService.save(newVideo);
       return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletebyId(@PathVariable Long id) {
videoService.delete(id);
return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, Model model) {
        Video video = videoService.findById(id);
        model.addAttribute("video",video);
        return "edit";
    }
    @PostMapping("edit")
    public String update(@ModelAttribute("video")Video video) {

        videoService.save(video);
        return "redirect:/";
    }


}
