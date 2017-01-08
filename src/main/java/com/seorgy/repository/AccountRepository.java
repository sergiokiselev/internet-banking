package com.seorgy.repository;

import com.seorgy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: NotePad.by
 * Date: 1/8/2017.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
