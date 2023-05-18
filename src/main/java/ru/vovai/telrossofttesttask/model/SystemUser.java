package ru.vovai.telrossofttesttask.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "system_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
