package com.leadexperience.chefio.service;


import com.leadexperience.chefio.models.response.CustomStatusCodeModel;
import com.leadexperience.chefio.models.response.StatusCodeModel;
import com.leadexperience.chefio.models.tables.RecipeStepsTable;
import com.leadexperience.chefio.models.tables.RecipeTable;
import com.leadexperience.chefio.models.tables.UserDataModel;
import com.leadexperience.chefio.models.tables.WishlistTable;
import com.leadexperience.chefio.respository.RecipeStepRepository;
import com.leadexperience.chefio.respository.RecipeTableRepository;
import com.leadexperience.chefio.respository.UserDataRepository;
import com.leadexperience.chefio.respository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Component
@Service
public class ApiService {
    private final static String NO_DATA = "Record not found!";
    private final static String RECORD_FOUND = "Record Found Successfully !";
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private RecipeTableRepository recipeTableRepo;
    @Autowired
    private RecipeStepRepository recipeStepRepo;

    @Autowired
    private WishlistRepository wishlistRepo;


    public ResponseEntity<?> signupUser(UserDataModel userDataModel) {
        // Getting all the initial traffic
        Optional<UserDataModel> userDataModelOptional= userDataRepository.findbyNumber(userDataModel.getNumber());

        if(userDataModelOptional.isPresent()){
            return new ResponseEntity<>(new StatusCodeModel("fail",400,"Already Existing user !"), HttpStatus.BAD_REQUEST);
        }else {
            userDataRepository.save(userDataModel);
            return new ResponseEntity<>(new StatusCodeModel("success", 200, "Sign up done successfully !"), HttpStatus.OK);
        }

    }
    public ResponseEntity<?> loginUser(String number, String password) {

        Optional<UserDataModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if (signupModelOptional.isPresent()) {

            Optional<UserDataModel> optionalSignupModel = userDataRepository.loginUserData(number, password);
            return ResponseEntity.ok(new CustomStatusCodeModel("sucess",200,"Login User successfully !",optionalSignupModel.get()));
        } else {
            return ResponseEntity.ok(new StatusCodeModel("fail",400,"User not found"));
        }
    }

    public ResponseEntity<?> loginUserJWT(String number, String password) {

        Optional<UserDataModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if (signupModelOptional.isPresent()) {

            Optional<UserDataModel> optionalSignupModel = userDataRepository.loginUserData(number, password);
            return ResponseEntity.ok(optionalSignupModel.get());
        } else {
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail", 400, "User not found"));
        }
    }


    public ResponseEntity<?> saveRecipe(RecipeTable recipeTable) {

        Long currentSavedRecipeId = recipeTableRepo.save(recipeTable).getId();

        // For the recipe steps.

        RecipeStepsTable recipeStepsTable = new RecipeStepsTable();
        recipeStepsTable.setRecipeId(currentSavedRecipeId);
        recipeStepsTable.setStepDescription(recipeTable.getSteps().getStepDescription());
        recipeStepsTable.setStepImage(recipeTable.getSteps().getStepImage());
        recipeStepsTable.setStepNumber(recipeTable.getSteps().getStepNumber());

        recipeStepRepo.save(recipeStepsTable);

        recipeStepRepo.flush();
        recipeTableRepo.flush();

        return ResponseEntity.ok(new StatusCodeModel("success",200,"Uploaded Recipe Details Successfully"));











    }

    public ResponseEntity<?> getUserRecipes(Long userId) {

        if(userDataRepository.findById(userId).isPresent()){
            ArrayList<RecipeTable> recipesForUser = recipeTableRepo.findRecipeByUserId(userId);

            if(recipesForUser.isEmpty()){
                return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"You have added zero recipes !"));
            }else{
                return ResponseEntity.ok(new CustomStatusCodeModel("success",200,recipesForUser));
            }
        }else{
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"User does not exists. !"));

        }




    }

    public ResponseEntity<?> addRecipeToWishlist(Long userId, Long recipeId) {

        if(userDataRepository.findById(userId).isPresent()){
            WishlistTable wishlistTable = new WishlistTable();
            wishlistTable.setUserId(userId);

            if(recipeTableRepo.findById(recipeId).isPresent()){
                wishlistTable.setRecipeId(recipeId);
                wishlistTable.setRecipeTable(recipeTableRepo.findById(recipeId).get());
                return ResponseEntity.ok(new CustomStatusCodeModel("success",200,"Added Recipe to wishlist Successfully !"));
            }else{
                return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"Recipe does not exists. !"));

            }


        }else{
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"User does not exists. !"));

        }




    }

    public ResponseEntity<?> deleteRecipeFromWishlist(Long userId, Long recipeId) {
        if(userDataRepository.findById(userId).isPresent()){
            if(recipeTableRepo.findById(recipeId).isPresent()){
//                wishlistRepo.deleteWishlistByUserIdAndRecipeId(userId,recipeId);
                return ResponseEntity.ok(new CustomStatusCodeModel("success",200,"Delete Recipe from wishlist Successfully !"));
            }else{
                return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"Recipe does not exists. !"));
            }
        }else{
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"User does not exists. !"));
        }
    }

    public ResponseEntity<?> getAllRecipes() {
        if(recipeTableRepo.findAll().isEmpty()){
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,NO_DATA));
        }else {
            return ResponseEntity.ok(new CustomStatusCodeModel("success",200,RECORD_FOUND,recipeTableRepo.findAll()));
        }
    }

    public ResponseEntity<?> resetUserPassword(String email, String newPassword) {




        if(email.isEmpty()){
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"Please send email"));
        }else if(newPassword.isEmpty()){
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"Please send password."));

        }else {

            Optional<UserDataModel> userDataModelOptional = userDataRepository.findByEmail(email);

            if(userDataModelOptional.isPresent()){
                userDataRepository.updatePasswordByEmail(email, newPassword);
                return ResponseEntity.ok(new StatusCodeModel("success",200,"Reset Password successfull."));

            }else{
                return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"User email not found. Please try again."));

            }



        }



    }
}
