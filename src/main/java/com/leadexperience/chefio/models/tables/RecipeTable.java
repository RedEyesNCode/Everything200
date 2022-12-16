package com.leadexperience.chefio.models.tables;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecipeTable {
    @Id
    @SequenceGenerator(name = "chefio_recipe_sequence", sequenceName = "chefio_recipe_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chefio_recipe_sequence")
    private Long id;


    private Long postByUserId;
    private String userName;
    private Long totalLikes;
    private String FoodName;
    private String Description;
    private Long duration;
    private String CoverImage;
    private ArrayList<String> ingredients;
    private ArrayList<String> tags;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "stepId" )
    private RecipeStepsTable steps;



}
