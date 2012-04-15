package net.sf.uadetector.internal.data.domain;

import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

public class OperatingSystemPatternTest {

	@Test
	public void compare_differentPatternFlags() {
		final OperatingSystemPattern pattern1 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+", Pattern.MULTILINE), 1);
		final OperatingSystemPattern pattern2 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE), 1);
		Assert.assertFalse(pattern1.equals(pattern2));
		Assert.assertFalse(pattern2.equals(pattern1));
	}

	@Test
	public void compare_identicalPatternFlags() {
		final OperatingSystemPattern pattern1 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+", Pattern.MULTILINE
				| Pattern.CASE_INSENSITIVE), 1);
		final OperatingSystemPattern pattern2 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE
				| Pattern.MULTILINE), 1);
		Assert.assertTrue(pattern1.equals(pattern2));
	}

	@Test
	public void compareTo_differentPosition() {
		final OperatingSystemPattern pattern1 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		final OperatingSystemPattern pattern2 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 2);
		Assert.assertEquals(-1, pattern1.compareTo(pattern2));
		Assert.assertEquals(1, pattern2.compareTo(pattern1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void compareTo_null() {
		final OperatingSystemPattern pattern = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertEquals(1, pattern.compareTo(null));
	}

	@Test
	public void compareTo_same() {
		final OperatingSystemPattern pattern = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertEquals(0, pattern.compareTo(pattern));
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_id_toSmall() {
		new OperatingSystemPattern(-1, Pattern.compile("[0-9]+"), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_order_toSmall() {
		new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructor_pattern_null() {
		new OperatingSystemPattern(1, null, 1);
	}

	@Test
	public void equals_different() {
		// different id
		final OperatingSystemPattern pattern1 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		final OperatingSystemPattern pattern2 = new OperatingSystemPattern(2, Pattern.compile("[0-9]+"), 1);
		Assert.assertFalse(pattern1.equals(pattern2));
		Assert.assertFalse(pattern1.hashCode() == pattern2.hashCode());

		// different pattern
		final OperatingSystemPattern pattern3 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		final OperatingSystemPattern pattern4 = new OperatingSystemPattern(1, Pattern.compile("[a-z]+"), 1);
		Assert.assertFalse(pattern3.equals(pattern4));
		Assert.assertFalse(pattern3.hashCode() == pattern4.hashCode());

		// different order
		final OperatingSystemPattern pattern5 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		final OperatingSystemPattern pattern6 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 2);
		Assert.assertFalse(pattern5.equals(pattern6));
		Assert.assertFalse(pattern5.hashCode() == pattern6.hashCode());

		// different class
		final OperatingSystemPattern pattern7 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		final BrowserPattern pattern8 = new BrowserPattern(1, Pattern.compile("[0-9]+"), 2);
		Assert.assertFalse(pattern7.equals(pattern8));

		// different to null
		final OperatingSystemPattern pattern9 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		final OperatingSystemPattern pattern10 = null;
		Assert.assertFalse(pattern9.equals(pattern10));
	}

	@Test
	public void equals_identical() {
		final OperatingSystemPattern pattern1 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		final OperatingSystemPattern pattern2 = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertTrue(pattern1.equals(pattern2));
		Assert.assertTrue(pattern1.hashCode() == pattern2.hashCode());
	}

	@Test
	public void equals_same() {
		final OperatingSystemPattern pattern = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertTrue(pattern.equals(pattern));
	}

	@Test
	public void testGetters() {
		final Pattern p = Pattern.compile("[0-9]+");
		final OperatingSystemPattern pattern = new OperatingSystemPattern(12345, p, 98765);
		Assert.assertEquals(12345, pattern.getId());
		Assert.assertSame(p, pattern.getPattern());
		Assert.assertEquals(98765, pattern.getPosition());
	}

	@Test
	public void testToString() {
		// reduces only some noise in coverage report
		final OperatingSystemPattern pattern = new OperatingSystemPattern(1, Pattern.compile("[0-9]+"), 1);
		Assert.assertEquals("OperatingSystemPattern [id=1, pattern=[0-9]+, position=1]", pattern.toString());
	}

}