package com.rafisheik.bankmanagement.Repository;

import com.rafisheik.bankmanagement.Model.BankModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends MongoRepository<BankModel, String> {
  BankModel findBymultiPurpose(String id);
}
