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
package org.jbromo.common.cdi.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.jbromo.common.cdi.annotation.Transactional;
import org.jbromo.common.invocation.AnnotationUtil;

/**
 * Define Transactional interceptor implementation to use in JSE environnement.
 *
 * @author qjafcunuas
 *
 */
@Transactional
@Interceptor
@Slf4j
public class TransactionalInterceptor {

    /**
     * The user transaction.
     */
    @Inject
    @Getter(AccessLevel.PRIVATE)
    private UserTransaction userTransaction;

    /**
     * The entity manager.
     */
    @Inject
    @Getter(AccessLevel.PRIVATE)
    private EntityManager entityManager;

    /**
     * Intercept all method called for logging.
     *
     * @param ctx
     *            the context.
     * @return the return object.
     * @throws Exception
     *             exception.
     */
    @AroundInvoke
    public Object intercept(final InvocationContext ctx) throws Exception {
        boolean started = false;
        try {
            final Transactional transaction = AnnotationUtil.getAnnotation(ctx,
                    Transactional.class);
            if (transaction != null) {
                switch (transaction.value()) {
                case REQUIRED:
                    started = startTransaction();
                    break;
                default:
                    break;
                }
            }
            if (getUserTransaction().getStatus() == Status.STATUS_ACTIVE) {
                getEntityManager().joinTransaction();
            }
            return ctx.proceed();
        } finally {
            if (started) {
                try {
                    getEntityManager().flush();
                    getEntityManager().clear();
                } finally {
                    if (getUserTransaction().getStatus() == Status.STATUS_MARKED_ROLLBACK) {
                        log.trace("Rollback transaction");
                        getUserTransaction().rollback();
                    } else {
                        log.trace("Commit transaction");
                        getUserTransaction().commit();
                    }
                }
            }
        }
    }

    /**
     * Start the transaction if necessary.
     *
     * @return false if transaction was already started before, else true.
     * @throws Exception
     *             exception.
     */
    private boolean startTransaction() throws Exception {
        if (getUserTransaction().getStatus() == Status.STATUS_NO_TRANSACTION) {
            log.trace("Start transaction");
            getUserTransaction().begin();
            return true;
        } else {
            return false;
        }
    }

}
