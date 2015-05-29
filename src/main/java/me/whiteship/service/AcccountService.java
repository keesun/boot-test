package me.whiteship.service;

import me.whiteship.domain.Account;
import me.whiteship.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Keeun Baik
 */
@Service
@Transactional
public class AcccountService {

    @Autowired
    AccountRepository repository;

    public Account createNewAccount(Account account) {
        account.setDate(new Date());
        return repository.save(account);
    }

    public Account updateAccount(Account accountUpdate) {
        Account account = repository.findOne(accountUpdate.getId());

        String newUsername = accountUpdate.getUsername();
        if (!StringUtils.isEmpty(newUsername)) {
            account.setUsername(newUsername);
        }

        String newPassword = accountUpdate.getPassword();
        if (!StringUtils.isEmpty(newPassword)) {
            account.setPassword(newPassword);
        }

        return repository.save(account);
    }
}
