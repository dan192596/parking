package com.project.parking.data.repository;

import com.project.parking.data.entity.BankAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BankAccountRepository extends PagingAndSortingRepository<BankAccount, Long> {
}
