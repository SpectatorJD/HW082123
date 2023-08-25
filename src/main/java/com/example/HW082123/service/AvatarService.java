package com.example.HW082123.service;

import com.example.HW082123.model.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    Avatar uploadAvatar(Long studentId, MultipartFile file) throws IOException;
    Avatar getAvatarById(Long avatarId);
}