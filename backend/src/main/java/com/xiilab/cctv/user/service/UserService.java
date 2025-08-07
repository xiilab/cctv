package com.xiilab.cctv.user.service;

import com.xiilab.cctv.user.User;
import com.xiilab.cctv.user.mapper.UserMapper;
import com.xiilab.cctv.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // JPA를 사용한 메서드들
    public User saveUser(User user) {
        log.info("JPA를 사용하여 사용자 저장: {}", user.getUsername());
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        log.info("JPA를 사용하여 사용자 조회: {}", id);
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        log.info("JPA를 사용하여 모든 사용자 조회");
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        log.info("JPA를 사용하여 사용자명으로 조회: {}", username);
        return userRepository.findByUsername(username);
    }

    public List<User> findByStatus(User.UserStatus status) {
        log.info("JPA를 사용하여 상태별 사용자 조회: {}", status);
        return userRepository.findByStatus(status);
    }

    public List<User> findByKeyword(String keyword) {
        log.info("JPA를 사용하여 키워드로 사용자 검색: {}", keyword);
        return userRepository.findByKeyword(keyword);
    }

    // MyBatis를 사용한 메서드들
    public List<User> findAllUsersByMyBatis() {
        log.info("MyBatis를 사용하여 모든 사용자 조회");
        return userMapper.selectAllUsers();
    }

    public User findUserByIdByMyBatis(Long id) {
        log.info("MyBatis를 사용하여 사용자 조회: {}", id);
        return userMapper.selectUserById(id);
    }

    public User findUserByUsernameByMyBatis(String username) {
        log.info("MyBatis를 사용하여 사용자명으로 조회: {}", username);
        return userMapper.selectUserByUsername(username);
    }

    public List<User> findUsersByStatusByMyBatis(String status) {
        log.info("MyBatis를 사용하여 상태별 사용자 조회: {}", status);
        return userMapper.selectUsersByStatus(status);
    }

    public int insertUserByMyBatis(User user) {
        log.info("MyBatis를 사용하여 사용자 저장: {}", user.getUsername());
        return userMapper.insertUser(user);
    }

    public int updateUserByMyBatis(User user) {
        log.info("MyBatis를 사용하여 사용자 수정: {}", user.getUsername());
        return userMapper.updateUser(user);
    }

    public int deleteUserByMyBatis(Long id) {
        log.info("MyBatis를 사용하여 사용자 삭제: {}", id);
        return userMapper.deleteUser(id);
    }

    public List<User> findUsersByKeywordByMyBatis(String keyword) {
        log.info("MyBatis를 사용하여 키워드로 사용자 검색: {}", keyword);
        return userMapper.selectUsersByKeyword(keyword);
    }

    // 비즈니스 로직
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteUser(Long id) {
        log.info("사용자 삭제: {}", id);
        userRepository.deleteById(id);
    }
}
