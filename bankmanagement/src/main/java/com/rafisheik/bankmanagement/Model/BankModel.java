package com.rafisheik.bankmanagement.Model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "UserDetails")
public class BankModel {

  @Id
  private String id;

  @Indexed(unique = true)
  private String userName;

  private String password;

  @Indexed(unique = true)
  private Long accountNumber;

  @Indexed(unique = true)
  private String emailId;

  @Indexed(unique = true)
  private String mobileNumber;

  private float balanceAmount;

  @SuppressWarnings("rawtypes")
  private List statement;

  private boolean isVerified;
  private String multiPurpose;

  public BankModel() {}

  public BankModel(
    String userName,
    String password,
    String emailId,
    String mobileNumber,
    float balanceAmount
  ) {
    this.userName = userName;
    this.password = password;
    this.accountNumber = System.currentTimeMillis();
    this.emailId = emailId;
    this.mobileNumber = mobileNumber;
    this.balanceAmount = balanceAmount;
    this.isVerified = false;
    this.multiPurpose = String.valueOf(System.currentTimeMillis());
  }
}
