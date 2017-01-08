package com.seorgy.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: NotePad.by
 * Date: 1/8/2017.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table
public class Transaction extends AbstractPersistable<Long> {

    @Column
    private long fromId;

    @Column
    private long toId;

    @Column
    private double amount;

    @Column
    private Date enteredOn;
}
