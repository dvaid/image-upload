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
}
