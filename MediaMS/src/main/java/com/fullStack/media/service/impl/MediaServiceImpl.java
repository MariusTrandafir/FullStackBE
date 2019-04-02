package com.fullStack.media.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fullStack.media.exceptions.FileStorageException;
import com.fullStack.media.exceptions.MyFileNotFoundException;
import com.fullStack.media.model.Media;
import com.fullStack.media.model.MediaFileResponse;
import com.fullStack.media.properties.FileStorageProperties;
import com.fullStack.media.service.MediaService;
import com.fullStack.media.dao.MediaDao;

@Service
public class MediaServiceImpl implements MediaService{

    private final Path fileStorageLocation;
    
	@Autowired
	MediaDao mediaDao;

    @Autowired
    public MediaServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String title, String description, String username, String tags, int likes) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Media media = new Media(username, fileName, "/media/downloadFile/"+fileName, description, title, tags,likes);
            if (mediaDao.findByUsernameAndProfile(username, true)==null) {
            	media.setProfile(true);
            }
            
            
            mediaDao.save(media);
            
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

	@Override
	public MediaFileResponse getProfileImage(String username) {
		Media media = mediaDao.findByUsernameAndProfile(username, true);
		if (media!=null)
		return new MediaFileResponse (media.getFilename(), media.getUrl(), media.getUsername(), media.getDescription(), media.getTitle(), media.getTags(), media.getLikes());
		return null;
	}

	@Override
	public List<MediaFileResponse> getMyImages(String username) {
		Iterable<Media> results =  mediaDao.findByUsername(username);
		System.out.println(results.toString());
		List<MediaFileResponse> response = new ArrayList<MediaFileResponse>();
		for (Media media: results) {
			response.add(new MediaFileResponse (media.getFilename(), media.getUrl(), media.getUsername(), media.getDescription(), media.getTitle(), media.getTags(), media.getLikes()));
		}
		return response;
	}
	
	@Override
	public List<MediaFileResponse> searchImages(String search) {
		String[] searchStrings = search.split(",");
		List<MediaFileResponse> response = new ArrayList<MediaFileResponse>();
		for (String searchString: searchStrings) {
		Iterable<Media> results =  mediaDao.searchMedia(search);
		System.out.println(results.toString());
		for (Media media: results) {
			response.add(new MediaFileResponse (media.getFilename(), media.getUrl(), media.getUsername(), media.getDescription(), media.getTitle(), media.getTags(), media.getLikes()));
		}
		}
		return response;
	}
}