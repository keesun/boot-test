package me.whiteship.repository;

import me.whiteship.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Keeun Baik
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
