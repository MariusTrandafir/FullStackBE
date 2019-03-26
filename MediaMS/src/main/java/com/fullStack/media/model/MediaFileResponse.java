package com.fullStack.media.model;

public class MediaFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String username;
    private String description;
    private String title;
    private String tags;
    
	public MediaFileResponse(String fileName, String fileDownloadUri, String username, String description, String title,
			String tags) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.username = username;
		this.description = description;
		this.title = title;
		this.tags = tags;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
    
    
}
