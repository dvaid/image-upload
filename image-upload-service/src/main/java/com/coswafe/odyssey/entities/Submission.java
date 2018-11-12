/**
 * 
 */
package com.coswafe.odyssey.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.Setter;

/**
 * @author dikshitv-l
 *
 */
@Entity
@Table(name = "Submission")
@Data
@Setter
public class Submission extends AuditableEntity {

	public Submission() {

	}

	public Submission(String author, String downloadUrl, String fileType, long fileSize) {
		super();
		this.author = author;
		this.downloadUrl = downloadUrl;
		this.fileType = fileType;
		this.fileSize = fileSize;
	}

	/**
	 * Description of the property id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ODYSSEY_SEQ")
	@SequenceGenerator(name = "ODYSSEY_SEQ", allocationSize = 7, initialValue = 101, sequenceName = "ODYSSEY_SEQ")
	private Long id;
	/**
	 * Description of the property author.
	 */
	@Column
	private String author;

	/**
	 * 
	 */
	@Column
	private String downloadUrl;

	@Column
	private String fileType;

	@Column
	private long fileSize;

	@Column
	@NotEmpty
	@Length(min = 25, max = 250, message = "Please provide a brief description of your submission. Maximum 250 characters.")
	private String brief;

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
