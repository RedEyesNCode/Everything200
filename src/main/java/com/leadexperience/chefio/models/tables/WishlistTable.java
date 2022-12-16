package com.leadexperience.chefio.models.tables;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WishlistTable {
    @Id
    @SequenceGenerator(name = "chefio_wishlist_sequence", sequenceName = "chefio_wishlist_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chefio_wishlist_sequence")
    private Long wishlist_id;

    private Long userId;

    private Long recipeId;

    @ManyToOne
    @JoinColumn(name = "id")
    private RecipeTable recipeTable;




}
