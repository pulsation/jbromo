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
package org.jbromo.model.jpa.test.builder.field;

import java.lang.reflect.Field;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.jbromo.common.ListUtil;
import org.jbromo.common.ObjectUtil;
import org.jbromo.common.exception.MessageLabelException;
import org.jbromo.model.jpa.compositepk.ICompositePk;

/**
 * Define a string field builder.
 * 
 * @author qjafcunuas
 * 
 */
@Slf4j
public class FieldCompositePkBuilder extends AbstractFieldBuilder<ICompositePk> {

	/**
	 * Default constructor.
	 * 
	 * @param fieldBuilderFactory
	 *            the field builder factory to used.
	 */
	FieldCompositePkBuilder(final FieldBuilderFactory fieldBuilderFactory) {
		super(fieldBuilderFactory);
	}

	@Override
	public List<ValidationValue<ICompositePk>> getValidationErrorValues(
			final Field field) {
		final List<ValidationValue<ICompositePk>> values = ListUtil.toList();
		return values;
	}

	@Override
	public List<ValidationValue<ICompositePk>> getValidationSuccessValues(
			final Field field) {
		final List<ValidationValue<ICompositePk>> values = ListUtil.toList();
		return values;
	}

	@Override
	public ICompositePk nextRandom(final boolean nullable, final Field field) {
		try {
			return (ICompositePk) ObjectUtil.newInstance(field.getType());
		} catch (MessageLabelException e) {
			log.error("Cannot instantiate class {}", field.getType());
			return null;
		}
	}
}