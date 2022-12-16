package com.leadexperience.chefio.respository;

import com.leadexperience.chefio.models.tables.RecipeStepsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStepsTable,Long> {
}
