package com.seorgy.service;

import com.seorgy.model.Account;
import javassist.NotFoundException;

/**
 * User: NotePad.by
 * Date: 1/8/2017.
 */
public interface AccountService {

    Account create(double money);

    void makeTransaction(long fromAccountId, long toAccountId, double ammount) throws Exception;
}
