/*
 * Copyright (C) 2013-2014 The JBromo Authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jbromo.dao.jpa.container.openjpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import lombok.extern.slf4j.Slf4j;

import org.jbromo.common.IntegerUtil;
import org.jbromo.dao.jpa.container.common.ICdiUserTransactionProducer;

import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * The user transaction producer.
 *
 * @author qjafcunuas
 *
 */
@ApplicationScoped
@Slf4j
public class CdiUserTransactionProducer implements ICdiUserTransactionProducer {

    @Override
    @Produces
    public UserTransaction getUserTransaction() {
        final UserTransactionManager utm = new UserTransactionManager();
        try {
            utm.setTransactionTimeout(IntegerUtil.INT_60);
        } catch (final SystemException e) {
            log.warn("Cannot set timeout", e);
        }
        return utm;
    }
}
