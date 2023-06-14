package com.project.parking.service;

import com.project.parking.dao.behavior.ITokenDao;
import com.project.parking.dao.behavior.IUserDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.BasicResponseDTO;
import com.project.parking.data.dto.TokenDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.Token;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.BasicTokenModel;
import com.project.parking.data.repository.UserRepository;
import com.project.parking.service.behavior.IMailService;
import com.project.parking.service.behavior.ITokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService implements ITokenService {

    @NotNull
    private final ITokenDao tokenDao;

    @NotNull
    private final IUserDao userDao;

    @NotNull
    private final UserRepository userRepository;

    @NonNull
    private final IMailService mailService;

    @NonNull
    private final StatusDefault statusDefault;

    @Override
    public BasicResponseDTO createToken(BasicTokenModel tokenModel) {
        Optional<User> user = userRepository.findById(tokenModel.getUserId());
        if(!user.isPresent())throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente");
        Token token =  tokenDao.generateToken(user.get());
        mailService.sendSimpleMail(user.get().getEmail(), "PARKING APP: Código de seguidad.", "Tu nuevo codigo de seguridad es: "+token.getToken());
        return new BasicResponseDTO("Token enviado");
    }

    @Override
    public Optional<TokenDTO> validateToken(BasicTokenModel tokenModel) {
        Optional<User> user = userRepository.findById(tokenModel.getUserId());
        if(user.isPresent()){
            Optional<Token> lastToken = tokenDao.getLastTokenByUser(user.get());
            if(!lastToken.isPresent())throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token inexistente");
            Token token = lastToken.get();
            if(token.getValidationAttempts()>3){
                token.setStatus(statusDefault.getDisabled());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximo de intentos superado");
            }
            if(!token.getToken().equals(tokenModel.getToken())) {
                token.setValidationAttempts(token.getValidationAttempts()+1);
                tokenDao.save(token);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token no valido");
            }
            User validUser = user.get();
            validUser.setStatus(statusDefault.getEnabled());
            userRepository.save(validUser);
            token.setStatus(statusDefault.getConsumed());
            return Optional.of(new TokenDTO(tokenDao.save(token)));
        }else{

        }
        return Optional.empty();
    }
}
