package com.coswafe.odyssey.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AuditableEntity {

	@CreatedBy
	@JsonIgnore
	@Column(updatable = false)
	private Integer createdBy;

	@CreatedDate
	@JsonIgnore
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedBy
	@JsonIgnore
	private Integer updatedBy;

	@LastModifiedDate
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@JsonIgnore
	private LocalDateTime updatedAt;

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
