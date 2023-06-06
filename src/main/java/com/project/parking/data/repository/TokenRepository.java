package com.project.parking.data.repository;

import com.project.parking.data.entity.Status;
import com.project.parking.data.entity.Token;
import com.project.parking.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {

    List<Token> findAllByUserAndStatus(User user, Status status);

}
