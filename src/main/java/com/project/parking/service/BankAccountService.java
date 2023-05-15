package com.project.parking.service;

import com.project.parking.dao.behavior.IBankAccountDao;
import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.service.behavior.IBankAccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountService implements IBankAccountService {

    @NonNull
    private final IBankAccountDao accountDao;

    @Override
    public PageDTO<List<BankAccountDTO>> getAllObject(Map<String, Object> queryParams) {
        return accountDao.select(queryParams);
    }

    @Override
    public Optional<BankAccountDTO> getObjectById(Long id) {
        return accountDao.select(id);
    }

    @Override
    public Optional<BankAccountDTO> updateObjectById(Long id, BankAccountDTO object) {
        return accountDao.update(id, object);
    }

    @Override
    public Optional<BankAccountDTO> deleteObjectById(Long id) {
        return accountDao.delete(id);
    }

    @Override
    public Optional<BankAccountDTO> insertObject(BankAccountDTO object) {
        return accountDao.insert(object);
    }
}
