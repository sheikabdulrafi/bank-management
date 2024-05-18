package com.rafisheik.bankmanagement.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

  private boolean success;
  private String message;
  private String error;
  private BankModel body;
}
