package com.project.parking.data.repository;

import com.project.parking.data.entity.BankAccount;
import com.project.parking.data.entity.Status;
import com.project.parking.data.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends PagingAndSortingRepository<BankAccount, Long> {
    Page<BankAccount> findAllByUserIdAndStatus(Long userId, Status status, Pageable pageable);
    List<BankAccount> findAllByUserId(Long userId);

    @Query("SELECT ba FROM BankAccount ba " +
            "WHERE ba.principal = TRUE " +
            "AND ba.user.id = :userId ")
    Optional<BankAccount> findPrincipalByUserId(@Param("userId") Long userId);
}
