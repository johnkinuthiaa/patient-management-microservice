package com.slippery.patientservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message = "Username should not be null")
    private String username;
    @NotNull
    @Email
    @NotBlank(message = "email should not be null")
    @Column(unique = true)
    private String email;
    @NotNull
    @NotBlank(message = "please enter valid address")
    private String address;
    private LocalDate dateOfBirth;
    @NotNull
    private LocalDate registeredOn;
}
