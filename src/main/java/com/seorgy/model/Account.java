package com.seorgy.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: NotePad.by
 * Date: 1/8/2017.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table
public class Account extends AbstractPersistable<Long> {
    @Column
    private double money;
}
