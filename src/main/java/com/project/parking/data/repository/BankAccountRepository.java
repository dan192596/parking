package com.project.parking.data.repository;

import com.project.parking.data.entity.BankAccount;
import com.project.parking.data.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BankAccountRepository extends PagingAndSortingRepository<BankAccount, Long> {
    Page<BankAccount> findAllByUserId(Long userId, Pageable pageable);
    List<BankAccount> findAllByUserId(Long userId);
}
