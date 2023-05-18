package ru.vovai.telrossofttesttask.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

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
    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Phone number should not be blank")
    @Pattern(regexp = "\\d{11}", message = "Invalid phone number format. It should be 10 digits.")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Image image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(middleName, user.middleName) && Objects.equals(birthday, user.birthday) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName, birthday, email, phoneNumber);
    }
}
