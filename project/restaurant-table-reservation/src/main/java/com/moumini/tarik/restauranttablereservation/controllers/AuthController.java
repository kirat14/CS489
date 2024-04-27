package com.moumini.tarik.restauranttablereservation.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moumini.tarik.restauranttablereservation.models.Customer;
import com.moumini.tarik.restauranttablereservation.models.ERole;
import com.moumini.tarik.restauranttablereservation.models.Manager;
import com.moumini.tarik.restauranttablereservation.models.Role;
import com.moumini.tarik.restauranttablereservation.models.User;
import com.moumini.tarik.restauranttablereservation.payload.request.LoginRequest;
import com.moumini.tarik.restauranttablereservation.payload.request.SignupRequest;
import com.moumini.tarik.restauranttablereservation.payload.response.JwtResponse;
import com.moumini.tarik.restauranttablereservation.payload.response.MessageResponse;
import com.moumini.tarik.restauranttablereservation.repositories.RoleRepository;
import com.moumini.tarik.restauranttablereservation.repositories.UserRepository;
import com.moumini.tarik.restauranttablereservation.security.jwt.JwtUtils;
import com.moumini.tarik.restauranttablereservation.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            break;
        }
      });
    }

    // Create new user's account using a dedicated method
    User user = createUser(signUpRequest, roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  private User createUser(SignupRequest signUpRequest, Set<Role> roles) {
    String encodedPassword = encoder.encode(signUpRequest.getPassword());
    if (signUpRequest.getRole() == null) {
      // Create customer user
      return new Customer(0L, signUpRequest.getUsername(), signUpRequest.getEmail(),
          encodedPassword, roles, signUpRequest.getFirstName(), signUpRequest.getLastName(),
          signUpRequest.getPhoneNumber(), signUpRequest.getAddress());
    } else {
      // Create manager user (assuming role logic assigns manager role)
      return new Manager(0L, signUpRequest.getUsername(), signUpRequest.getEmail(),
          encodedPassword, roles);
    }
  }
}
