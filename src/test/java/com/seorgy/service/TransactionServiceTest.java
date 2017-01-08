package com.seorgy.service;

import com.seorgy.model.Account;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void testCreate() {
        double money = 10.;
        Account model = service.create(money);
        Assert.assertEquals(model.getMoney(), money, 0);
    }

    @Test(expected = NotFoundException.class)
    public void testTransaction() throws Exception {
        service.makeTransaction(1, 2, 12.3);
    }

}
