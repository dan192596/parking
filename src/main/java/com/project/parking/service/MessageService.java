package com.project.parking.service;

import com.project.parking.dao.behavior.IMessageDao;
import com.project.parking.data.dto.MessageDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.VehicleDTO;
import com.project.parking.service.behavior.IMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    @NonNull
    private final IMessageDao messageDao;

    @Override
    public PageDTO<List<MessageDTO>> getAllObject(Map<String, Object> queryParams) {
        return messageDao.select(queryParams);
    }

    @Override
    public Optional<MessageDTO> getObjectById(Long id) {
        return messageDao.select(id);
    }

    @Override
    public Optional<MessageDTO> updateObjectById(Long id, MessageDTO object) {
        return messageDao.update(id, object);
    }

    @Override
    public Optional<MessageDTO> deleteObjectById(Long id) {
        return messageDao.delete(id);
    }

    @Override
    public Optional<MessageDTO> insertObject(MessageDTO object) {
        return messageDao.insert(object);
    }

    @Override
    public PageDTO<List<MessageDTO>> getAllMessagesByChatList(Map<String, Object> queryParams) {
        return messageDao.getAllMessagesByChatList(queryParams);
    }
}
