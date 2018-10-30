package com.coswafe.odyssey.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coswafe.odyssey.entities.User;
import com.coswafe.odyssey.repository.UserRepository;

/**
 * @author kamal berriga
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User save(User user) {
		return userRepository.saveAndFlush(encodeUserPassword(user));
	}

	private User encodeUserPassword(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return user;
	}

	public User update(User user) {
		return userRepository.save(encodeUserPassword(user));
	}

	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	public Optional<User> find(Long id) {
		return userRepository.findById(id);
	}
}
