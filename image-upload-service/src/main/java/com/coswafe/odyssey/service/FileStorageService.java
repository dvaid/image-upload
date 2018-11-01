package com.coswafe.odyssey.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.coswafe.odyssey.entities.Submission;
import com.coswafe.odyssey.exception.FileStorageException;
import com.coswafe.odyssey.exception.MyFileNotFoundException;
import com.coswafe.odyssey.property.FileStorageProperties;
import com.coswafe.odyssey.repository.SubmissionRepository;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	private SubmissionRepository submissionRepo;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	private String getUserName() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	public String createUserDirectory(String username) throws IOException {
		final Path userDirectory = Files.createDirectories(this.fileStorageLocation.resolve(username));
		return userDirectory.toAbsolutePath().toString();
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			final String userName = getUserName();
			Path targetLocation = this.fileStorageLocation.resolve(userName + File.separator + fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			final Submission submission = new Submission(userName, fileName, file.getContentType(), file.getSize());
			submissionRepo.save(submission);
			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public List<Submission> getSubmissions(String author){
		return submissionRepo.findByAuthor(author);
	}
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName + File.separator + getUserName()).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
}
