/**
 * 
 */
package com.coswafe.odyssey.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author dikshitv-l
 *
 */
public class SubmissionDTO {

	private String brief;
	private String author;
	private MultipartFile file;


	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
