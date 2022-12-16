package com.leadexperience.chefio.respository;

import com.leadexperience.chefio.models.tables.RecipeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RecipeTableRepository extends JpaRepository<RecipeTable,Long> {


    @Query("SELECT s FROM RecipeTable s WHERE s.postByUserId = :postByUserId")
    ArrayList<RecipeTable> findRecipeByUserId(Long postByUserId);





}
