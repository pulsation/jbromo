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
package org.jbromo.sample.server.services.dao.src.usergroup;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lombok.AccessLevel;
import lombok.Getter;

import org.jbromo.dao.common.Dao;
import org.jbromo.dao.jpa.AbstractEntityDao;
import org.jbromo.sample.server.model.src.UserGroup;

/**
 * The user DAO implementation.
 *
 * @author qjafcunuas
 *
 */
@Dao
@Stateless
public class UserGroupDao extends AbstractEntityDao<UserGroup, Long> implements
        IUserGroupDao {

    /**
     * serial version UID.
     */
    private static final long serialVersionUID = -1060683774516455634L;

    /**
     * The entity manager to used.
     */
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;

}
