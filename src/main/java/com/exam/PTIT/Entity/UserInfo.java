package com.exam.PTIT.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String name;
    private String password;
    @Column(name = "roles")
    private String roles;

    @Column(name = "name")
    private String fullName;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false, nullable = false)
    private Date createdDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastest_login_date", updatable = true, nullable = true)
    private Date lastLoginDate;

}
