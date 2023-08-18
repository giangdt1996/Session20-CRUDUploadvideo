package ra.demovideo.service.impl;

import org.springframework.stereotype.Service;
import ra.demovideo.model.Video;
import ra.demovideo.service.IGenericService;
import ra.demovideo.until.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService implements IGenericService<Video,Long> {
    @Override
    public List<Video> findAll() {
        Connection conn = ConnectDB.getConnection();
        List<Video> videos = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call FindAll}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Video v = new Video();
                v.setId(rs.getLong("id"));
                v.setTitle(rs.getString("title"));
                v.setDescription(rs.getString("description"));
                v.setVideoUrl(rs.getString("link"));


                videos.add(v);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return videos;
    }

    @Override
    public void save(Video video) {
        Connection conn = ConnectDB.getConnection();

        try {
            if (video.getId() == null) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call insertVideo(?,?,?)}");
                callSt.setString(1,video.getTitle());
                callSt.setString(2,video.getDescription());
                callSt.setString(3,video.getVideoUrl());
                callSt.executeUpdate();
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call updateVideo(?,?,?,?)}");
                callSt.setString(4,video.getVideoUrl());
                callSt.setString(2,video.getTitle());
                callSt.setString(3,video.getDescription());
                callSt.setLong(1,video.getId());
                callSt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }

    }

    @Override
    public void delete(Long id) {
        Connection conn = ConnectDB.getConnection();
        try {
            // xóa ảnh phụ
            CallableStatement callSt = conn.prepareCall("{call deleteVideo(?)}");
            callSt.setLong(1,id);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Video findById(Long id) {
        Connection conn = ConnectDB.getConnection();
        Video v = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call FindById(?)}");
            callSt.setLong(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                v = new Video();
                v.setId(rs.getLong("id"));
                v.setVideoUrl(rs.getString("link"));
                v.setTitle(rs.getString("title"));
                v.setDescription(rs.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return v;
    }
}
