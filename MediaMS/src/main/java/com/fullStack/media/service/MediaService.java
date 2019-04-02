package com.fullStack.media.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fullStack.media.model.Media;
import com.fullStack.media.model.MediaDto;
import com.fullStack.media.model.MediaFileResponse;

public interface MediaService {

	String storeFile(MultipartFile file, String title, String description, String username, String tags, int likes);
	Resource loadFileAsResource(String fileName);
	MediaFileResponse getProfileImage(String username);
	List<MediaFileResponse> getMyImages(String username);
	List<MediaFileResponse> searchImages(String search);
}
