package com.capstone.hanperform.users.service;

import com.capstone.hanperform.users.dto.UserDto;
import com.capstone.hanperform.users.entity.User;
import com.capstone.hanperform.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(UserDto userDTO) {
        User user = new User(userDTO.getEmail(), userDTO.getPassword());
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getEmail(), savedUser.getPassword());
    }

    public UserDto getUserByName(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return new UserDto(user.getId(), user.getEmail(), user.getPassword());
    }

    public boolean loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public UserDto authenticate(String username, String password) {
        Optional<User> user = userRepository.findByEmail(username);
        // 사용자를 찾지 못하면 null 반환
        log.info("ser: " + user);
        return user.map(value -> new UserDto(value.getId(), value.getEmail(), value.getPassword())).orElse(null);
    }

}