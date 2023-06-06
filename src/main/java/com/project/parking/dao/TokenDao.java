package com.project.parking.dao;

import com.project.parking.dao.behavior.ITokenDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.entity.Token;
import com.project.parking.data.entity.User;
import com.project.parking.data.repository.TokenRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TokenDao implements ITokenDao {

    @NotNull
    private final TokenRepository tokenRepository;

    @NonNull
    private final StatusDefault statusDefault;

    @Override
    public Optional<Token> getLastTokenByUser(User user) {
        List<Token> tokenList = tokenRepository.findAllByUserAndStatus(user, statusDefault.getPending());
        Token tokenFound = tokenList.size() > 0 ? tokenList.get(tokenList.size()-1): null;
        if(tokenFound != null) {//Si existe algun token creado con status created
            return Optional.of(tokenFound);
        }
        return Optional.empty();
    }

    @Override
    public Token generateToken(User user) {
        Random rnd = new Random();
        Token newToken = new Token();
        newToken.setToken(String.format("%06d" , rnd.nextInt(999999)));
        newToken.setUser(user);
        newToken.setStatus(statusDefault.getPending());
        return tokenRepository.save(newToken);
    }

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }
}
