package com.coswafe.odyssey.service;

import static java.lang.String.format;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.coswafe.odyssey.entities.Submission;
import com.coswafe.odyssey.exception.FileStorageException;
import com.coswafe.odyssey.exception.MyFileNotFoundException;
import com.coswafe.odyssey.property.FileStorageProperties;
import com.coswafe.odyssey.repository.SubmissionRepository;
import com.coswafe.odyssey.util.FileUtils;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private SubmissionRepository submissionRepo;

    /*@Autowired
    private S3Service s3Service;*/

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

	public String storeFile(MultipartFile file, Submission submission) {
		final String userName = getUserName();
		// Normalize file name
		final String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		final String fileDownloadUri = UriComponentsBuilder.fromPath("/downloadFile/").path(fileName).toUriString();

        try {
            String s3BucketFileName = FileUtils.getFileName(file,userName);
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(userName + File.separator + fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			// final Submission submission = new Submission(userName, fileDownloadUri,
			// file.getContentType(),
			// file.getSize());
			submission.setFileSize(file.getSize());
			submission.setFileType(file.getContentType());
			submission.setDownloadUrl(fileDownloadUri);
            final String imagePathWithName = format("%s/%s/%s", "uploads",userName, s3BucketFileName);
//            s3Service.putFile(imagePathWithName, file.getInputStream());
			submissionRepo.save(submission);
			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

    public List<Submission> getSubmissions(String author) {
        return submissionRepo.findByAuthor(author);
    }

    public Resource loadFileAsResource(String fileName, String username) {
        try {
            final Path filePath = this.fileStorageLocation.resolve(username + File.separator + fileName).normalize();
            final Resource resource = new UrlResource(filePath.toUri());
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
