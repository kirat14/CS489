package com.moumini.tarik.restauranttablereservation.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String address;

  @NotBlank
  private String phoneNumber;
}
