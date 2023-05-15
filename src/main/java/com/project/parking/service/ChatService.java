package com.project.parking.service;

import com.project.parking.dao.behavior.IChatDao;
import com.project.parking.data.dto.ChatDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.service.behavior.IChatService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {

    @NonNull
    private final IChatDao chatDao;

    @Override
    public PageDTO<List<ChatDTO>> getAllObject(Map<String, Object> queryParams) {
        return chatDao.select(queryParams);
    }

    @Override
    public Optional<ChatDTO> getObjectById(Long id) {
        return chatDao.select(id);
    }

    @Override
    public Optional<ChatDTO> updateObjectById(Long id, ChatDTO object) {
        return chatDao.update(id, object);
    }

    @Override
    public Optional<ChatDTO> deleteObjectById(Long id) {
        return chatDao.delete(id);
    }

    @Override
    public Optional<ChatDTO> insertObject(ChatDTO object) {
        return chatDao.insert(object);
    }
}
