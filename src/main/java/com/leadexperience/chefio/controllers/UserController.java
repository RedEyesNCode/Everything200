package com.leadexperience.chefio.controllers;


import com.leadexperience.chefio.models.body.LoginRequestBody;
import com.leadexperience.chefio.models.response.StatusCodeModel;
import com.leadexperience.chefio.models.tables.RecipeTable;
import com.leadexperience.chefio.models.tables.UserDataModel;
import com.leadexperience.chefio.service.ApiService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/chefio")
public class UserController {

    @Autowired
    private ApiService apiService;



    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello");
    }


    @CrossOrigin
    @RequestMapping(value = "/authJWT", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestBody loginRequestBody){

        // YOU CAN PUT MNY VALIDATIONS HERE.
        
        return apiService.loginUser(loginRequestBody.getUsername(),loginRequestBody.getPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody UserDataModel userDataModel){

        if(userDataModel.getNumber().isEmpty()){
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"Failed Please provide number as well."));
        }else{
            return apiService.signupUser(userDataModel);

        }
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam("userEmail") String email,@RequestParam("newPassword") String newPassword){


        return apiService.resetUserPassword(email,newPassword);



    }



    @PostMapping("/addRecipe")
    public ResponseEntity<?> addRecipe(@RequestBody RecipeTable recipeTable){
        return apiService.saveRecipe(recipeTable);
    }

    @GetMapping("/getAllRecipes")
    public ResponseEntity<?> getAllRecipe(){

        return apiService.getAllRecipes();

    }

    @GetMapping("/getUserRecipes")
    public ResponseEntity<?> getUserRecipe(@RequestParam("userId") Long userId){
        if(userId==0){
            return ResponseEntity.badRequest().body(new StatusCodeModel("success",400,"Please provide user Id."));
        }else {
            return apiService.getUserRecipes(userId);
        }
    }

    @PostMapping("/addRecipeToWishlist")
    public ResponseEntity<?> addRecipeToWishList(@RequestParam("userId") Long userId, @RequestParam("recipeId") Long recipeId){

        if(userId==null || userId ==0 || recipeId==null || recipeId==0){
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"Please provide required params !"));
        }else {

            return apiService.addRecipeToWishlist(userId,recipeId);

        }
    }


    @PostMapping("/deleteRecipeFromWishlist")
    public ResponseEntity<?> deleteRecipeFromWishlist(@RequestParam("userId") Long userId, @RequestParam("recipeId") Long recipeId){

        if(userId==null || userId ==0 || recipeId==null || recipeId==0){
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"Please provide required params !"));

        }else{
            return apiService.deleteRecipeFromWishlist(userId,recipeId);
        }



    }


}
