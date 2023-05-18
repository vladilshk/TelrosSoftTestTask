package ru.vovai.telrossofttesttask.model;

import lombok.*;

import javax.persistence.*;
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
    private String name;
    private String surname;
    private String middleName;
    private Date birthDay;
    private String email;
    private String phoneNumber;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Image image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(middleName, user.middleName) && Objects.equals(birthDay, user.birthDay) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName, birthDay, email, phoneNumber, image);
    }
}
