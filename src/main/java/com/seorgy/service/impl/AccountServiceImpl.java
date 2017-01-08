package com.seorgy.service.impl;

import com.seorgy.model.Account;
import com.seorgy.model.Transaction;
import com.seorgy.repository.AccountRepository;
import com.seorgy.repository.TransactionRepository;
import com.seorgy.service.AccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * User: NotePad.by
 * Date: 1/8/2017.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Account create(double money) {
        Account model = new Account();
        model.setMoney(money);
        System.out.println(accountRepository);
        return accountRepository.save(model);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    public Transaction makeTransaction(long fromAccountId, long toAccountId, double amount) throws Exception {
        if (fromAccountId == toAccountId) {
            throw new Exception(messageSource.getMessage("messages.error.transactions.same_account", null, null));
        }
        Account accountFrom = accountRepository.findOne(fromAccountId);
        if (accountFrom == null) {
            throw new NotFoundException(messageSource.getMessage("messages.error.transactions.account_not_found",
                    new Long[] {fromAccountId}, null));
        }
        Account accountTo = accountRepository.findOne(toAccountId);
        if (accountTo == null) {
            throw new NotFoundException(messageSource.getMessage("messages.error.transactions.account_not_found",
                    new Long[] {toAccountId}, null));
        }
        if (accountFrom.getMoney() < amount) {
            throw new Exception("account " + fromAccountId + " does not have enough money");
        }
        accountFrom.setMoney(accountFrom.getMoney() - amount);
        accountTo.setMoney(accountTo.getMoney() + amount);
        Transaction transaction = new Transaction();
        transaction.setEnteredOn(new Date());
        transaction.setAmount(amount);
        transaction.setFromId(fromAccountId);
        transaction.setToId(toAccountId);
        return transactionRepository.save(transaction);
    }
}
