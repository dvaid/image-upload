package com.coswafe.odyssey.entities;

/*******************************************************************************
 * 2017, this is the user entity class ,
 * this class implements users details of the spring security framework
 *******************************************************************************/

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * 
 * @author dikshitv-l
 *
 */
@Entity
@Table(name = "User")
@Scope("session")
public class User extends AuditableEntity implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Role {
		USER
	}

	/**
	 * Description of the property id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ODYSSEY_SEQ")
	@SequenceGenerator(name = "ODYSSEY_SEQ", allocationSize = 7, initialValue = 101, sequenceName = "ODYSSEY_SEQ")
	private Long id;
	/**
	 * Description of the property email.
	 */
	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String phoneNumber;

	@Column(unique = true)
	private String fbURL;

	@Column(unique = true)
	private String instaURL;

	/**
	 * Description of the property password.
	 */
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	/**
	 * Description of the property role , to grant authority to the user .
	 */
	private String role;
	/**
	 * Description of the property full name.
	 */
	private String fullName;

	public User() {

	}

	public User(String username, String password, String fullName) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFbURL() {
		return fbURL;
	}

	public void setFbURL(String fbURL) {
		this.fbURL = fbURL;
	}

	public String getInstaURL() {
		return instaURL;
	}

	public void setInstaURL(String instaURL) {
		this.instaURL = instaURL;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", phoneNumber=" + phoneNumber + ", fbURL=" + fbURL + ", instaURL="
				+ instaURL + ", password=" + password + ", role=" + role + ", fullName=" + fullName + "]";
	}

}