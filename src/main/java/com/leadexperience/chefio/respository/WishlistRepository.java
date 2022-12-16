package com.leadexperience.chefio.respository;

import com.leadexperience.chefio.models.tables.RecipeTable;
import com.leadexperience.chefio.models.tables.WishlistTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistTable,Long> {
    @Query("SELECT s FROM WishlistTable s WHERE s.userId = :userId")
    ArrayList<RecipeTable> findWishListByUserId(Long userId);

//    @Modifying
//    @Query("DELETE from WishlistTable b WHERE b.userId =:userId AND b.recipeId =: recipeId")
//    void deleteWishlistByUserIdAndRecipeId(@Param("userId") Long userId, @Param("recipeId") Long recipeId);

}
