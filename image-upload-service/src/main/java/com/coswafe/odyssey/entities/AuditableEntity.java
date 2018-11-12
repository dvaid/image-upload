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
}
