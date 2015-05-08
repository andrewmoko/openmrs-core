/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.web;

import java.util.Collection;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.BaseOpenmrsObject;

/**
 * Tests methods on the {@link WebUtil} class.
 */
public class WebUtilTest {
	
	/**
	 * @see WebUtil#normalizeLocale(String)
	 * @verifies ignore leading spaces
	 */
	@Test
	public void normalizeLocale_shouldIgnoreLeadingSpaces() throws Exception {
		Assert.assertEquals(Locale.ITALIAN, WebUtil.normalizeLocale(" it"));
	}
	
	/**
	 * @see WebUtil#normalizeLocale(String)
	 * @verifies accept language only locales
	 */
	@Test
	public void normalizeLocale_shouldAcceptLanguageOnlyLocales() throws Exception {
		Assert.assertEquals(Locale.FRENCH, WebUtil.normalizeLocale("fr"));
	}
	
	/**
	 * @see WebUtil#normalizeLocale(String)
	 * @verifies not accept invalid locales
	 */
	@Test
	public void normalizeLocale_shouldNotAcceptInvalidLocales() throws Exception {
		Assert.assertNull(WebUtil.normalizeLocale("ptrg"));
		Assert.assertNull(WebUtil.normalizeLocale("usaa"));
		//'usa' is accepted by Oracle JDK 1.7.0_09
		//Assert.assertNull(WebUtil.normalizeLocale("usa"));
	}
	
	/**
	 * @see WebUtil#normalizeLocale(String)
	 * @verifies not fail with empty strings
	 */
	@Test
	public void normalizeLocale_shouldNotFailWithEmptyStrings() throws Exception {
		Assert.assertNull(WebUtil.normalizeLocale(""));
	}
	
	/**
	 * @see WebUtil#normalizeLocale(String)
	 * @verifies not fail with whitespace only
	 */
	@Test
	public void normalizeLocale_shouldNotFailWithWhitespaceOnly() throws Exception {
		Assert.assertNull(WebUtil.normalizeLocale("      "));
	}
	
	/**
	 * @see WebUtil#sanitizeLocales(String)
	 * @verifies skip over invalid locales
	 */
	@Test
	public void sanitizeLocales_shouldSkipOverInvalidLocales() throws Exception {
		Assert.assertEquals("fr_RW, it, en", WebUtil.sanitizeLocales("és, qqqq, fr_RW, it, enñ"));
		//'qqq' is accepted by Oracle JDK 1.7.0_09
	}
	
	/**
	 * @see WebUtil#sanitizeLocales(String)
	 * @verifies not fail with empty string
	 */
	@Test
	public void sanitizeLocales_shouldNotFailWithEmptyString() throws Exception {
		Assert.assertNull(null, WebUtil.sanitizeLocales(""));
	}
	
	/**
	 * Utility method to check if a list contains a BaseOpenmrsObject using the id
	 * @param list
	 * @param id
	 * @return true if list contains object with the id else false
	 */
	public static boolean containsId(Collection<? extends BaseOpenmrsObject> list, Integer id) {
		for (BaseOpenmrsObject baseOpenmrsObject : list) {
			if (baseOpenmrsObject.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
}
