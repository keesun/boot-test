package me.whiteship.controller;

import me.whiteship.domain.Account;
import me.whiteship.dto.AccountDTO;
import me.whiteship.repository.AccountRepository;
import me.whiteship.service.AcccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Keeun Baik
 */
@Controller
public class AccountControlle {

    @Autowired
    AcccountService service;

    @Autowired
    AccountRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody @Valid AccountDTO.Request request, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(new ErrorResponse("account.create.error", "뭔가 잘못 입력하신듯..."), HttpStatus.BAD_REQUEST);
        }
        Account account = modelMapper.map(request, Account.class);
        Account newAccount = service.createNewAccount(account);
        AccountDTO.Response response = modelMapper.map(newAccount, AccountDTO.Response.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity getAccounts() {
        List<AccountDTO.Response> response = repository.findAll().stream().map(a -> modelMapper.map(a, AccountDTO.Response.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity getAccount(@PathVariable int id) {
        Account account = repository.findOne(id);
        if (account == null) {
            return new ResponseEntity<>(new ErrorResponse("account.not.found", "그런 유저는 없어.."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(account, AccountDTO.Response.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity updateAccount(@PathVariable int id, @RequestBody @Valid AccountDTO.Update request, BindingResult result) {
        Account account = repository.findOne(id);
        if (account == null) {
            return new ResponseEntity<>(new ErrorResponse("account.not.found", "그런 유저는 없어.."), HttpStatus.BAD_REQUEST);
        }

        if (result.hasErrors()) {
            return new ResponseEntity<>(new ErrorResponse("account.update.error", "뭔가 잘못 입력하신듯..."), HttpStatus.BAD_REQUEST);
        }

        Account accountUpdate = modelMapper.map(request, Account.class);
        accountUpdate.setId(id);
        Account updatedAccount = service.updateAccount(accountUpdate);

        return new ResponseEntity<>(modelMapper.map(updatedAccount, AccountDTO.Response.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccounts(@PathVariable int id) {
        Account account = repository.findOne(id);
        if (account == null) {
            return new ResponseEntity<>(new ErrorResponse("account.not.found", "그런 유저는 없어.."), HttpStatus.BAD_REQUEST);
        }

        repository.delete(account);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
