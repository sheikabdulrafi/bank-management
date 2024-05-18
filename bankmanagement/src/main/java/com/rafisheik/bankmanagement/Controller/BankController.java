package com.rafisheik.bankmanagement.Controller;

import com.rafisheik.bankmanagement.Model.BankModel;
import com.rafisheik.bankmanagement.Model.LoginHelper;
import com.rafisheik.bankmanagement.Model.ResponseModel;
import com.rafisheik.bankmanagement.Repository.BankRepo;
import com.rafisheik.bankmanagement.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping("/")
  public ResponseEntity<ResponseModel> home() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(new ResponseModel(true, "Welcome to the Server", null, null));
  }

  @PostMapping("/user/new")
  public ResponseEntity<ResponseModel> createAccount(
    @RequestBody BankModel data
  ) {
    if (
      data.getUserName().isBlank() ||
      data.getPassword().isBlank() ||
      data.getEmailId().isBlank() ||
      data.getBalanceAmount() < 0 ||
      data.getMobileNumber().isBlank()
    ) {
      return ResponseEntity
        .status(404)
        .body(
          new ResponseModel(
            false,
            "Invalid data",
            "Please fill all required feilds",
            null
          )
        );
    }

    data.setAccountNumber(System.currentTimeMillis());
    data.setMultiPurpose(String.valueOf(System.currentTimeMillis()));
    data.setPassword(passwordEncoder.encode(data.getPassword()));

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
      .body(new ResponseModel(true, "Please verify you email Id", null, null));
  }

  @GetMapping("/user/{id}")
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

  @PostMapping("/user/login")
  public ResponseEntity<ResponseModel> login(
    @RequestBody LoginHelper loginHelper
  ) {
    if (
      loginHelper.getUserName().isBlank() || loginHelper.getPassword().isBlank()
    ) {
      return ResponseEntity
        .status(404)
        .body(
          new ResponseModel(
            false,
            "Invalid data",
            "Please fill all required feilds",
            null
          )
        );
    }

    BankModel user = repo.findByuserName(loginHelper.getUserName());
    if (user == null) {
      return ResponseEntity
        .status(404)
        .body(
          new ResponseModel(
            false,
            "Invalid Credentials",
            "Please Enter correct credentials",
            null
          )
        );
    }

    if (
      !passwordEncoder.matches(loginHelper.getPassword(), user.getPassword())
    ) {
      return ResponseEntity
        .status(404)
        .body(
          new ResponseModel(
            false,
            "Invalid Credentials",
            "Please Enter correct credentials",
            null
          )
        );
    }

    return ResponseEntity
      .status(HttpStatus.ACCEPTED)
      .body(new ResponseModel(true, "Login Successfull", null, user));
  }
}
