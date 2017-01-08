package com.seorgy.service;

import com.seorgy.model.Account;
import com.seorgy.model.Transaction;
import com.seorgy.repository.AccountRepository;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User: NotePad.by
 * Date: 1/8/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private AccountService service;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MessageSource messageSource;

    @Test
    public void testCreate() {
        double money = 10.;
        Account model = service.create(money);
        Assert.assertEquals(model.getMoney(), money, 0);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testThrowNotFoundWithWrongFromId() throws Exception {
        Account account = service.create(1);
        long wrongId = 10000;
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(messageSource.getMessage("messages.error.transactions.account_not_found", new Long[] {wrongId}, null));
        service.makeTransaction(wrongId, account.getId() + 2, 12.3);
    }

    @Test
    public void testThrowNotFoundWithWrongToId() throws Exception {
        Account account = service.create(1);
        long wrongId = 10000;
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(messageSource.getMessage("messages.error.transactions.account_not_found", new Long[] {wrongId}, null));
        service.makeTransaction(account.getId(), wrongId, 12.3);
    }

    @Test
    public void testThrowExceptionWithSameIds() throws Exception {
        thrown.expect(Exception.class);
        String message = messageSource.getMessage("messages.error.transactions.same_account", null, null);
        thrown.expectMessage(message);
        service.makeTransaction(1, 1, 1);
    }

    @Test
    public void testTransaction() throws Exception {
        Account firstAccount = service.create(20);
        Account secondAccount = service.create(0);
        double amount = 10;
        Transaction transaction = service.makeTransaction(firstAccount.getId(), secondAccount.getId(), amount);
        Assert.assertNotNull(transaction.getId());
        Account updatedFirstAccount = accountRepository.findOne(firstAccount.getId());
        Account updatedSecondAccount = accountRepository.findOne(secondAccount.getId());
        Assert.assertEquals(updatedFirstAccount.getMoney(), 10, 0);
        Assert.assertEquals(updatedSecondAccount.getMoney(), 10, 0);
    }
}
