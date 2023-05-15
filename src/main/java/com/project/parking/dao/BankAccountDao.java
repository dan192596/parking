package com.project.parking.dao;

import com.project.parking.dao.behavior.IBankAccountDao;
import com.project.parking.data.defaults.StatusDefault;
import com.project.parking.data.dto.BankAccountDTO;
import com.project.parking.data.dto.PageDTO;
import com.project.parking.data.dto.UserDTO;
import com.project.parking.data.entity.BankAccount;
import com.project.parking.data.entity.User;
import com.project.parking.data.model.DefaultsParamsModel;
import com.project.parking.data.repository.BankAccountRepository;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankAccountDao implements IBankAccountDao {

    @NonNull
    private final BankAccountRepository accountRepository;

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final StatusDefault statusDefault;

    @Override
    public Optional<BankAccountDTO> select(Long id) {
        Optional<BankAccount> account = accountRepository.findById(id);
        return account.map(BankAccountDTO::new);
    }

    @Override
    public PageDTO<List<BankAccountDTO>> select(Map<String, Object> queryParams) {
        DefaultsParamsModel params = new DefaultsParamsModel(queryParams);
        Pageable pageable = PageRequest.of(params.getIndex(), params.getItems()<0?10:params.getItems(), params.getDirection().equals("ASC") ? Sort.by(params.getSort()).ascending() : Sort.by(params.getSort()).descending());
        Page<BankAccount> bankAccounts = accountRepository.findAll(pageable);

        return new PageDTO<>(
                bankAccounts.getContent()
                        .stream()
                        .map(BankAccountDTO::new)
                        .collect(Collectors.toList()),
                bankAccounts.getTotalElements(),
                params.getIndex(),
                bankAccounts.getContent().size()
        );
    }

    @Override
    public Optional<BankAccountDTO> update(Long id, BankAccountDTO object) {
        Optional<BankAccount> bankAccountOptional = accountRepository.findById(id);
        bankAccountOptional.ifPresent(bankAccount -> {
            bankAccount.setNumber(object.getNumber());
            bankAccount.setType(object.getType());
            bankAccount.setPrincipal(object.getPrincipal());
            accountRepository.save(bankAccount);

        });
        return bankAccountOptional.map(BankAccountDTO::new);
    }

    @Override
    public Optional<BankAccountDTO> delete(Long id) {
        Optional<BankAccount> bankAccountOptional = accountRepository.findById(id);
        if(!bankAccountOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cuenta bancaria inexistente");
        }

        BankAccount bankAccount = bankAccountOptional.get();
        bankAccount.setStatus(statusDefault.getEnabled());
        return Optional.of(new BankAccountDTO(accountRepository.save(bankAccount)));
    }

    @Override
    public Optional<BankAccountDTO> insert(BankAccountDTO object) {
        Optional<User> userOptional = userRepository.findById(object.getUser());
        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente");
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumber(object.getNumber());
        bankAccount.setType(object.getType());
        bankAccount.setPrincipal(object.getPrincipal());
        bankAccount.setUser(userOptional.get());
        bankAccount.setStatus(statusDefault.getEnabled());
        return Optional.of(new BankAccountDTO(accountRepository.save(bankAccount)));
    }
}
