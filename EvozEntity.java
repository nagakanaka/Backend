package com.tritern.evozspecial.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "evoztable")
public class EvozEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 55, nullable = false)
    private String name;

    @Column(name = "emailid", nullable = false, unique = true)
    private String emailid;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", length = 55, nullable = false, unique = true)
    private String username;

    @Column(name = "saltpassword", nullable = false)
    private String salt;

    public EvozEntity() {}

    public EvozEntity(Long id, String name, String emailid, String password, String username, String salt) {
        this.id = id;
        this.name = name;
        this.emailid = emailid;
        this.password = password;
        this.username = username;
        this.salt = salt;
    }

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmailid() { return emailid; }

    public void setEmailid(String emailid) { this.emailid = emailid; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

    @Override
    public String toString() {
        return "EvozEntity [id=" + id + ", name=" + name + ", emailid=" + emailid + ", password=" + password
                + ", username=" + username + ", salt=" + salt + "]";
    }
}
