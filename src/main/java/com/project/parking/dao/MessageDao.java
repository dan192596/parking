package com.project.parking.dao;

import com.project.parking.dao.behavior.IMessageDao;
import com.project.parking.data.dto.ChatDTO;
import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.Chat;
import com.project.parking.data.entity.Message;
import com.project.parking.data.entity.Parking;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.ChatRepository;
import com.project.parking.data.repository.MessageRepository;
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
public class MessageDao implements IMessageDao {

    @NonNull
    private final MessageRepository messageRepository;

    @NonNull
    private final ChatRepository chatRepository;


    @Override
    public Optional<MessageDTO> select(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        return message.map(MessageDTO::new);
    }

    @Override
    public PageDTO<List<MessageDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<Message> messages = messageRepository.findAll(pageable);

        return new PageDTO<>(
                messages.getContent()
                        .stream()
                        .map(MessageDTO::new)
                        .collect(Collectors.toList()),
                messages.getTotalElements(),
                params.getIndex(),
                messages.getContent().size()
        );
    }

    @Override
    public Optional<MessageDTO> update(Long id, MessageDTO object) {
        return Optional.empty();
    }

    @Override
    public Optional<MessageDTO> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<MessageDTO> insert(MessageDTO object) {
        Optional<Chat> chatOptional = chatRepository.findById(object.getChat());
        if(!chatOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chat inexistente");
        }

        Message message = new Message();
        message.setMessage(object.getMessage());
        message.setChat(chatOptional.get());
        message.setUserMessage(true);
        return Optional.of(new MessageDTO(messageRepository.save(message)));
    }
}
