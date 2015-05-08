/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.validator;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.OrderType;
import org.openmrs.test.Verifies;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * Tests methods on the {@link OrderTypeValidator} class.
 */
public class OrderTypeValidatorTest {
	
	/**
	 * @see {@link OrderTypeValidator#validate(Object,Errors)}
	 * 
	 */
	@Test
	@Verifies(value = "should fail validation if name is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() throws Exception {
		OrderType type = new OrderType();
		type.setName(null);
		type.setDescription(":(");
		
		Errors errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		type.setName("");
		errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		type.setName(" ");
		errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}
	
	/**
	 * @see {@link OrderTypeValidator#validate(Object,Errors)}
	 * 
	 */
	@Test
	@Verifies(value = "should fail validation if description is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {
		OrderType type = new OrderType();
		type.setName("restraining");
		type.setDescription(null);
		
		Errors errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		type.setDescription("");
		errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		type.setDescription(" ");
		errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
	}
	
	/**
	 * @see {@link OrderTypeValidator#validate(Object,Errors)}
	 * 
	 */
	@Test
	@Verifies(value = "should pass validation if all required fields have proper values", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllRequiredFieldsHaveProperValues() throws Exception {
		OrderType type = new OrderType();
		type.setName("restraining");
		type.setDescription(":(");
		
		Errors errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}
}
