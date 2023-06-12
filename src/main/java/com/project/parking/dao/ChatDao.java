package com.project.parking.dao;

import com.project.parking.dao.behavior.IChatDao;
import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.ChatDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.BankAccount;
import com.project.parking.data.entity.Chat;
import com.project.parking.data.entity.Parking;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.ChatRepository;
import com.project.parking.data.repository.ParkingRepository;
import com.project.parking.data.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatDao implements IChatDao {

    @NonNull
    private final ChatRepository chatRepository;

    @NonNull
    private final ParkingRepository parkingRepository;

    @NonNull
    private final UserRepository userRepository;

    @Override
    public Optional<ChatDTO> select(Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        return chat.map(ChatDTO::new);
    }

    @Override
    public PageDTO<List<ChatDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Chat> chats = chatRepository.findAll(pageable);

        return new PageDTO<>(
                chats.getContent()
                        .stream()
                        .map(ChatDTO::new)
                        .collect(Collectors.toList()),
                chats.getTotalElements(),
                params.getIndex(),
                chats.getContent().size()
        );
    }

    @Override
    public Optional<ChatDTO> update(Long id, ChatDTO object) {
        return Optional.empty();
    }

    @Override
    public Optional<ChatDTO> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ChatDTO> insert(ChatDTO object) {
        Optional<User> userOptional = userRepository.findById(object.getUser());
        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente");
        }

        Optional<Parking> parkingOptional = parkingRepository.findById(object.getParkingId());
        if(!parkingOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parqueo inexistente");
        }

        Chat chat = new Chat();
        chat.setParking(parkingOptional.get());
        chat.setUser(userOptional.get());
        return Optional.of(new ChatDTO(chatRepository.save(chat)));
    }

    @Override
    public PageDTO<List<ChatDTO>> getAllChatsByUserList(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Chat> chats = chatRepository.findAllByUserId(params.getUser(), pageable);

        return new PageDTO<>(
                chats.getContent()
                        .stream()
                        .map(ChatDTO::new)
                        .collect(Collectors.toList()),
                chats.getTotalElements(),
                params.getIndex(),
                chats.getContent().size()
        );
    }
}
