package com.leadexperience.chefio.models.tables;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserProfileTable {
    @Id
    @SequenceGenerator(name = "chefio_profile_sequence", sequenceName = "chefio_profile_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chefio_profile_sequence")
    private Long id;


    private Long userId;

    private Long numberOfRecipies;

    private Long followers;

    private Long following;

    private String profileImage;


    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;



}
