package com.xiilab.cctv.user.controller;

import com.xiilab.cctv.common.dto.ApiResponse;
import com.xiilab.cctv.user.User;
import com.xiilab.cctv.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        log.info("모든 사용자 조회 요청");
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("/mybatis")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsersByMyBatis() {
        log.info("MyBatis를 사용한 모든 사용자 조회 요청");
        List<User> users = userService.findAllUsersByMyBatis();
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("사용자 조회 요청: {}", id);
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mybatis/{id}")
    public ResponseEntity<User> getUserByIdByMyBatis(@PathVariable Long id) {
        log.info("MyBatis를 사용한 사용자 조회 요청: {}", id);
        User user = userService.findUserByIdByMyBatis(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        log.info("사용자명으로 조회 요청: {}", username);
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<User>> getUsersByStatus(@PathVariable String status) {
        log.info("상태별 사용자 조회 요청: {}", status);
        try {
            User.UserStatus userStatus = User.UserStatus.valueOf(status.toUpperCase());
            List<User> users = userService.findByStatus(userStatus);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String keyword) {
        log.info("키워드로 사용자 검색 요청: {}", keyword);
        List<User> users = userService.findByKeyword(keyword);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("사용자 생성 요청: {}", user.getUsername());
        
        if (userService.isUsernameExists(user.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        
        if (userService.isEmailExists(user.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/mybatis")
    public ResponseEntity<String> createUserByMyBatis(@RequestBody User user) {
        log.info("MyBatis를 사용한 사용자 생성 요청: {}", user.getUsername());
        int result = userService.insertUserByMyBatis(user);
        return result > 0 ? ResponseEntity.ok("사용자가 생성되었습니다.") : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("사용자 삭제 요청: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("User Service is running!"));
    }
}
