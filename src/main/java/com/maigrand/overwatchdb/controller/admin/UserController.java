package com.maigrand.overwatchdb.controller.admin;

import com.maigrand.overwatchdb.payload.AuthenticationCredentials;
import com.maigrand.overwatchdb.payload.AuthenticationTokenDetails;
import com.maigrand.overwatchdb.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationTokenDetails> authenticate(
            @RequestBody AuthenticationCredentials credentials
    ) {
        return ResponseEntity.ok(this.userService.authenticate(credentials));
    }
}
