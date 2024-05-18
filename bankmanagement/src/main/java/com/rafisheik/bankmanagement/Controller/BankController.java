package com.rafisheik.bankmanagement.Controller;

import com.rafisheik.bankmanagement.Model.BankModel;
import com.rafisheik.bankmanagement.Repository.BankRepo;
import com.rafisheik.bankmanagement.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

  @Autowired
  BankRepo repo;

  @Autowired
  EmailService emailService;

  @GetMapping("/")
  public ResponseEntity<String> home() {
    return ResponseEntity.status(HttpStatus.OK).body("Welcome to the Server");
  }

  @PostMapping("/new")
  public ResponseEntity<String> createAccount(@RequestBody BankModel data) {
    if (data == null) {
      return ResponseEntity.badRequest().build();
    }

    data.setAccountNumber(System.currentTimeMillis());
    data.setMultiPurpose(String.valueOf(System.currentTimeMillis()));

    BankModel user = repo.insert(data);

    emailService.sendEmail(
      user.getEmailId(),
      "Welcome to Heaven Bank | Activate your account",
      "Hello " +
      user.getUserName() +
      " please click on the following link to activate and get your account details<br> http://10.50.97.3:8080/" +
      user.getMultiPurpose()
    );

    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body("Please verify your email");
  }

  @GetMapping("/{id}")
  public ResponseEntity<BankModel> verifyAccount(@PathVariable String id) {
    if (id.isBlank()) {
      return ResponseEntity.badRequest().build();
    }

    try {
      BankModel user = repo.findBymultiPurpose(id);
      System.out.println("\n\n\n" + user.isVerified());
      if (user.isVerified()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      user.setVerified(true);
      user.setMultiPurpose("");
      user = repo.save(user);
      emailService.sendEmail(
        user.getEmailId(),
        "Heaven Safe Bank | Account Verified Successfully",
        user.toString()
      );
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
