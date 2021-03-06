/*******************************************************************************
 * Copyright 2012 André Rouél
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.sf.uadetector.internal.data.domain;

import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

public class BrowserPatternTest {

	@Test
	public void compare_differentPatternFlags() {
		final BrowserPattern pattern1 = new BrowserPattern(1, Pattern.compile("[0-9]+", Pattern.MULTILINE), 1);
		final BrowserPattern pattern2 = new BrowserPattern(1, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE), 1);
		Assert.assertFalse(pattern1.equals(pattern2));
		Assert.assertFalse(pattern2.equals(pattern1));
	}

	@Test
	public void compare_identicalPatternFlags() {
		final BrowserPattern pattern1 = new BrowserPattern(1, Pattern.compile("[0-9]+", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE), 1);
		final BrowserPattern pattern2 = new BrowserPattern(1, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE), 1);
		Assert.assertTrue(pattern1.equals(pattern2));
	}

	@Test
	public void compareTo_differentPosition() {
		final BrowserPattern pattern1 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern2 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 2);
		Assert.assertEquals(-1, pattern1.compareTo(pattern2));
		Assert.assertEquals(1, pattern2.compareTo(pattern1));
	}

	@Test
	public void compareTo_identical() {
		final BrowserPattern pattern1 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern2 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertEquals(0, pattern1.compareTo(pattern2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void compareTo_null() {
		final BrowserPattern pattern = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertEquals(1, pattern.compareTo(null));
	}

	@Test
	public void compareTo_same() {
		final BrowserPattern pattern = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertEquals(0, pattern.compareTo(pattern));
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_id_toSmall() {
		new BrowserPattern(-1, Pattern.compile("[0-9]+"), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_order_toSmall() {
		new BrowserPattern(1, Pattern.compile("[0-9]+"), -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_pattern_null() {
		new BrowserPattern(1, null, 1);
	}

	@Test
	public void equals_different() {
		// different id
		final BrowserPattern pattern1 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern2 = new BrowserPattern(2, Pattern.compile("[0-9]+"), 1);
		Assert.assertFalse(pattern1.equals(pattern2));
		Assert.assertFalse(pattern1.hashCode() == pattern2.hashCode());

		// different pattern
		final BrowserPattern pattern3 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern4 = new BrowserPattern(1, Pattern.compile("[a-z]+"), 1);
		Assert.assertFalse(pattern3.equals(pattern4));
		Assert.assertFalse(pattern3.hashCode() == pattern4.hashCode());

		// different order
		final BrowserPattern pattern5 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern6 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 2);
		Assert.assertFalse(pattern5.equals(pattern6));
		Assert.assertFalse(pattern5.hashCode() == pattern6.hashCode());

		// different class
		final BrowserPattern pattern7 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final OperatingSystemPattern pattern8 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 2);
		Assert.assertFalse(pattern7.equals(pattern8));

		// different to null
		final BrowserPattern pattern9 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern10 = null;
		Assert.assertFalse(pattern9.equals(pattern10));
	}

	@Test
	public void equals_identical() {
		final BrowserPattern pattern1 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern2 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertTrue(pattern1.equals(pattern2));
		Assert.assertTrue(pattern1.hashCode() == pattern2.hashCode());
	}

	@Test
	public void equals_same() {
		final BrowserPattern pattern = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertTrue(pattern.equals(pattern));
	}

	@Test
	public void testGetters() {
		final Pattern p = Pattern.compile("[0-9]+");
		final BrowserPattern pattern = new BrowserPattern(12345, p, 98765);
		Assert.assertEquals(12345, pattern.getId());
		Assert.assertSame(p, pattern.getPattern());
		Assert.assertEquals(98765, pattern.getPosition());
	}

	@Test
	public void testToString() {
		// reduces only some noise in coverage report
		final BrowserPattern pattern = new BrowserPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertEquals("BrowserPattern [id=1, pattern=[0-9]+, position=1]", pattern.toString());
	}

}
