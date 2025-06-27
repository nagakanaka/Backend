package com.tritern.evozspecial.document;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "evoz_images")
public class EvozDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @ElementCollection
    @CollectionTable(name = "user_photos", joinColumns = @JoinColumn(name = "document_id"))
    @Column(name = "photo_url")
    private List<String> photo;

    public EvozDocument() {
    }

    public EvozDocument(String email, List<String> photo) {
        this.email = email;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "EvozDocument{id=" + id + ", email='" + email + "', photo=" + photo + "}";
    }
}
