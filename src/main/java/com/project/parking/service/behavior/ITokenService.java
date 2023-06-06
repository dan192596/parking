package com.project.parking.service.behavior;

import com.project.parking.data.dto.BasicResponseDTO;
import com.project.parking.data.dto.TokenDTO;
import com.project.parking.data.model.BasicTokenModel;

import java.util.Optional;

public interface ITokenService {

    BasicResponseDTO createToken(BasicTokenModel tokenModel);

    Optional<TokenDTO> validateToken(BasicTokenModel tokenModel);
}
