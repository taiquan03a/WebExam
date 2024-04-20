package com.exam.PTIT.Controller;


import com.exam.PTIT.Entity.AuthRequest;
import com.exam.PTIT.Entity.UserInfo;
import com.exam.PTIT.Repository.UserInfoRepository;
import com.exam.PTIT.Service.Jwt.JwtService;
import com.exam.PTIT.Service.User.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserInfoService service;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public ResponseEntity<?> welcom(){
        return ResponseEntity.ok("Welcom");
    }
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Optional<UserInfo>> userProfile(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        return ResponseEntity.status(200).body(userInfoRepository.findByName(username));
    }
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> adminProfile(HttpServletRequest request){
        try {
            String authHeader = request.getHeader("Authorization");
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
            return ResponseEntity.status(200).body(userInfoRepository.findByName(username));
        }catch (Exception ex){
            return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/generateToken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            System.out.println(authentication.isAuthenticated());
            if (authentication.isAuthenticated()) {

                return ResponseEntity.ok(jwtService.generateToken(authRequest.getUsername()));
            }
            throw new UsernameNotFoundException("invalid user request !");
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>("tk mk sai",HttpStatus.BAD_REQUEST);
        }
    }

}

