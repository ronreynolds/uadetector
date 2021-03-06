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

import net.sf.uadetector.internal.data.OrderedPattern;
import net.sf.uadetector.internal.util.RegularExpressionConverter;

/**
 * The {@code OperatingSystemPattern} class represents the detection information for a specific operating system item.<br>
 * <br>
 * A {@code OperatingSystemPattern} object is immutable, their values cannot be changed after creation.
 * 
 * @author André Rouél
 */
public final class OperatingSystemPattern implements OrderedPattern<OperatingSystemPattern> {

	/**
	 * Factory that creates instances of {@code OperatingSystemPattern} via method calls.
	 * 
	 * @author André Rouél
	 */
	public static final class Builder {

		/**
		 * Identification number (ID) of an operating system pattern
		 */
		private int id = Integer.MIN_VALUE;

		/**
		 * A compiled representation of a regular expression to detect an operating system
		 */
		private Pattern pattern;

		/**
		 * Position of a {@code OperatingSystemPattern} (only relevant if there are multiple patterns for an operating
		 * system in a {@code SortedSet})
		 */
		private int position = Integer.MIN_VALUE;

		/**
		 * Builds a new instance of {@code OperatingSystemPattern} and returns it.
		 * 
		 * @return a new instance of {@code OperatingSystemPattern}
		 * @throws IllegalArgumentException
		 *             if one of the needed arguments to build an instance of {@code OperatingSystemPattern} is invalid
		 */
		public OperatingSystemPattern build() {
			return new OperatingSystemPattern(id, pattern, position);
		}

		/**
		 * Sets the identification number of an operating system pattern entry.
		 * 
		 * @param id
		 *            identification number
		 * @return this {@code Builder}, for chaining
		 * @throws IllegalArgumentException
		 *             if the given integer is smaller than {@code 0}
		 */
		public Builder setId(final int id) {
			if (id < 0) {
				throw new IllegalArgumentException("Argument 'id' must not be smaller than 0.");
			}

			this.id = id;
			return this;
		}

		/**
		 * Sets the identification number (ID) of an operating system pattern. The given {@code String} is parsed as a
		 * decimal number.
		 * 
		 * @param id
		 *            ID of an operating system pattern as string
		 * @return this {@code Builder}, for chaining
		 * @throws IllegalArgumentException
		 *             if the given argument is {@code null}
		 * @throws NumberFormatException
		 *             if the given string is not parsable as integer
		 * @throws IllegalArgumentException
		 *             if the parsed integer is smaller than {@code 0}
		 */
		public Builder setId(final String id) {
			if (id == null) {
				throw new IllegalArgumentException("Argument 'id' must not be null.");
			}

			this.setId(Integer.parseInt(id.trim()));
			return this;
		}

		/**
		 * Sets a regular expression for an operating system pattern.
		 * 
		 * @param pattern
		 *            compiled representation of a regular expression
		 * @return this {@code Builder}, for chaining
		 */
		public Builder setPattern(final Pattern pattern) {
			if (pattern == null) {
				throw new IllegalArgumentException("Argument 'pattern' must not be null.");
			}

			this.pattern = pattern;
			return this;
		}

		/**
		 * Converts a PERL regular expression in a Java regular expression and sets it in the {@code Builder}.
		 * 
		 * @param regex
		 *            PERL style regular expression to be converted
		 * @return this {@code Builder}, for chaining
		 */
		public Builder setPerlRegularExpression(final String regex) {
			if (regex == null) {
				throw new IllegalArgumentException("Argument 'regex' must not be null.");
			}

			this.setPattern(RegularExpressionConverter.convertPerlRegexToPattern(regex));
			return this;
		}

		/**
		 * Sets the position of an operating system pattern in a set of patterns.
		 * 
		 * @param position
		 *            position of an operating system pattern
		 * @return this {@code Builder}, for chaining
		 * @throws IllegalArgumentException
		 *             if the given integer is smaller than {@code 0}
		 */
		public Builder setPosition(final int position) {
			if (position < 0) {
				throw new IllegalArgumentException("Argument 'position' must not be null.");
			}

			this.position = position;
			return this;
		}

		/**
		 * Sets the position of an operating system pattern in a set of patterns. The given {@code String} is parsed as
		 * a decimal number.
		 * 
		 * @param position
		 *            position of an operating system pattern as string
		 * @return this {@code Builder}, for chaining
		 * @throws IllegalArgumentException
		 *             if the given argument is {@code null}
		 * @throws NumberFormatException
		 *             if the given string is not parsable as integer
		 * @throws IllegalArgumentException
		 *             if the parsed integer is smaller than {@code 0}
		 */
		public Builder setPosition(final String position) {
			if (position == null) {
				throw new IllegalArgumentException("Argument 'position' must not be null.");
			}

			this.setPosition(Integer.parseInt(position.trim()));
			return this;
		}

	}

	/**
	 * Identification number (ID) of an operating system pattern
	 */
	private final int id;

	/**
	 * A compiled representation of a regular expression to detect an operating system
	 */
	private final Pattern pattern;

	/**
	 * Position of a {@code OperatingSystemPattern} (only relevant if there are multiple patterns for an operating
	 * system in a {@code SortedSet})
	 */
	private final int position;

	public OperatingSystemPattern(final int id, final Pattern pattern, final int position) {
		if (id < 0) {
			throw new IllegalArgumentException("Argument 'id' must not be smaller than 0.");
		}

		if (pattern == null) {
			throw new IllegalArgumentException("Argument 'pattern' must not be null.");
		}

		if (position < 0) {
			throw new IllegalArgumentException("Argument 'position' must not be smaller than 0.");
		}

		this.id = id;
		this.pattern = pattern;
		this.position = position;
	}

	/**
	 * Compares this instance with the given instance of a {@code OperatingSystemPattern} for order. Returns a negative
	 * integer, zero, or a positive integer as this instances position ({@code OperatingSystemPattern#position}) is less
	 * than, equal to, or greater than the position of the given one.<br>
	 * <br>
	 * Note: this class has a ordering by attribute <i>position</i> that is inconsistent with equals.
	 * 
	 * @param pattern
	 *            another instance of {@code OperatingSystemPattern}
	 * @return negative value if the position of this instance is less, 0 if equal, or positive value if greater than
	 *         the position of the other one
	 * @throws IllegalArgumentException
	 *             if the given argument is {@code null}
	 */
	@Override
	public int compareTo(final OperatingSystemPattern pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("Argument 'pattern' must not be null.");
		}

		int result = 0;
		if (getPosition() > pattern.getPosition()) {
			result = 1;
		} else if (getPosition() < pattern.getPosition()) {
			result = -1;
		}
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final OperatingSystemPattern other = (OperatingSystemPattern) obj;
		if (id != other.id) {
			return false;
		}
		if (position != other.position) {
			return false;
		}
		if (!pattern.pattern().equals(other.pattern.pattern())) {
			return false;
		}
		if (pattern.flags() != other.pattern.flags()) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the identification number (ID) of an operating system pattern.
	 * 
	 * @return identification number (ID) of an operating system pattern
	 */
	public int getId() {
		return id;
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + position;
		result = prime * result + pattern.pattern().hashCode();
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("OperatingSystemPattern [id=");
		builder.append(id);
		builder.append(", pattern=");
		builder.append(pattern);
		builder.append(", position=");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}

}
