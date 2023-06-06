package com.project.parking.dao.behavior;

import com.project.parking.data.entity.Token;
import com.project.parking.data.entity.User;

import java.util.Optional;

public interface ITokenDao {

    Optional<Token> getLastTokenByUser(User user);

    Token generateToken(User user);

    Token save(Token token);

}
