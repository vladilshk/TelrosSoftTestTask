package ru.vovai.telrossofttesttask.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Table(name = "usr")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "Surname should not be blank")
    private String surname;
    private String middleName;
    private Date birthday;
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "\\d{11}", message = "Invalid phone number format. It should be 10 digits.")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Image image;
}
