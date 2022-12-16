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
public class RecipeStepsTable {


    @Id
    @SequenceGenerator(name = "chefio_recipe_step_sequence", sequenceName = "chefio_recipe_step_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chefio_recipe_step_sequence")
    private Long stepId;

    private Long recipeId;

    private Long stepNumber;

    private String stepDescription;

        private String stepImage;






}
