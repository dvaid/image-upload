/**
 * 
 */
package com.coswafe.odyssey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coswafe.odyssey.entities.Submission;

/**
 * @author dikshitv-l
 *
 */
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
	/**
	 * 
	 * @param author
	 * @return
	 */
	public List<Submission> findByAuthor(String author);
}
