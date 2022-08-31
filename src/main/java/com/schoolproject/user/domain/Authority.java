package com.schoolproject.user.domain;

/**
 * @author Taewoo
 */


import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TABLE_AUTHORITY")
@IdClass(Authority.class)
public class Authority implements GrantedAuthority {

    public static final String ROLE_TEACHER = "ROLE_TEACHER";
    public static final String ROLE_STUDENT = "ROLE_STUDENT";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final Authority ADMIN_AUTHORITY = Authority.builder().authority(ROLE_ADMIN).build();
    public static final Authority TEACHER_AUTHORITY = Authority.builder().authority(ROLE_TEACHER).build();
    public static final Authority STUDENT_AUTHORITY = Authority.builder().authority(ROLE_STUDENT).build();

    @Id
    private Long userId;

    @Id
    private String authority;

}








