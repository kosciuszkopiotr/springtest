package com.visionmate.springtest.domain.auth.resource;

import com.visionmate.springtest.api.LoginDataDTO;
import com.visionmate.springtest.api.TokenDataDTO;
import com.visionmate.springtest.domain.repositories.RoleRepository;
import com.visionmate.springtest.domain.repositories.UserRepository;
import com.visionmate.springtest.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vis-test/login")
public class AuthResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginDataDTO loginDataDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDataDTO.getUsername(),
                        loginDataDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new TokenDataDTO(jwt));

    }

}
