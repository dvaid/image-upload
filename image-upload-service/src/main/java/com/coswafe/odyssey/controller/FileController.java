package com.coswafe.odyssey.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coswafe.odyssey.dto.SubmissionDTO;
import com.coswafe.odyssey.entities.Submission;
import com.coswafe.odyssey.payload.UploadFileResponse;
import com.coswafe.odyssey.service.FileStorageService;

@RestController
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@Validated SubmissionDTO submissionRequest) {

		final MultipartFile file = submissionRequest.getFile();
		final Submission submission = new Submission();
		submission.setAuthor(submissionRequest.getAuthor());
		submission.setBrief(submissionRequest.getBrief());
		final String fileName = fileStorageService.storeFile(file, submission);

		final String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	/*
	 * @PostMapping("/uploadMultipleFiles") public List<UploadFileResponse>
	 * uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) { return
	 * Arrays.asList(files).stream().map(file ->
	 * uploadFile(file)).collect(Collectors.toList()); }
	 */

	@GetMapping("/downloadFile/{username}/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, @PathVariable String username,
			HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName, username);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("{username}/submissions")
	public ResponseEntity<List<Submission>> downloadSubmissions(@PathVariable String username,
			HttpServletRequest request) {
		final List<Submission> submissions = fileStorageService.getSubmissions(username);
		final ResponseEntity<List<Submission>> responseEntity = new ResponseEntity<List<Submission>>(submissions,
				HttpStatus.OK);
		List<Submission> body = responseEntity.getBody();
		for (Submission submission : body) {
			System.out.println(submission);
		}
		return responseEntity;

	}

}
