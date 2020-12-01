package com.teamten.sizzle.controller;

import com.teamten.sizzle.model.Account;
import com.teamten.sizzle.model.CookBook;
import com.teamten.sizzle.model.Recipe;
import com.teamten.sizzle.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/profile")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PutMapping("/new")
    public boolean addNewUser(@RequestBody Account account) {
        return accountService.addUser(account);
    }

    @PostMapping("/authenticate")
    public boolean authenticateUser(@RequestBody Account account) {
        return accountService.authenticateUser(account);
    }

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestBody Account account) {
        return accountService.deleteUser(account);
    }


    @PutMapping("/newCookBook/{user}")
    public void addNewCookBook(@PathVariable String user, @RequestBody CookBook cookBook) {
        System.out.println(cookBook.toString());
        accountService.addNewCookBook(user, cookBook);
    }

    @DeleteMapping("/cookBook/{user}/{cookBookId}")
    public void deleteCookBook(@PathVariable String user, @PathVariable int cookBookId) {
        accountService.removeCookBook(user, cookBookId);
    }

    @PostMapping("/saveRecipe/{user}/{cookBookId}/{recipeId}")
    public void saveRecipeForUser(@PathVariable String user, @PathVariable int cookBookId, @PathVariable int recipeId) {
        accountService.addRecipeToUser(user, cookBookId, recipeId);
    }

    @DeleteMapping("/removeRecipeFromCookBook/{user}/{cookBookId}/{recipeId}")
    public void removeRecipeFromCookCook(@PathVariable String user, @PathVariable int cookBookId, @PathVariable int recipeId) {
        accountService.removeRecipeFromCookBook(user, cookBookId, recipeId);
    }

    @GetMapping("checkRecipeSaved/{user}/{cookBookId}/{recipeId}")
    public boolean checkRecipeSavedInCookBook(@PathVariable String user, @PathVariable int cookBookId, @PathVariable int recipeId) {
        return accountService.checkRecipeSaved(user, cookBookId, recipeId);
    }

    @PostMapping("updatePassword/{user}/{currentPassword}/{newPassword}")
    public void updatePasswordForUser(@PathVariable String user, @PathVariable String currentPassword, @PathVariable String newPassword) {
        accountService.updateUserPassword(user, currentPassword, newPassword);
    }

    @PostMapping("updateEmail/{user}/{email}")
    public void updateEmailForUser(@PathVariable String user, @PathVariable String email) {
        accountService.updateUserEmail(user, email);
    }

    @GetMapping("checkUser/{user}")
    public boolean checkUserExist(@PathVariable String user){
        return accountService.checkUserExist(user);
    }

    @GetMapping("getCookBooksFor/{user}")
    public Account getCookBooks(@PathVariable String user){
        return accountService.getCookBooksForUser(user);
    }

}
