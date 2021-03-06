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

import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentFamily;

public final class Browser {

	public static final class Builder {

		private UserAgentFamily family = UserAgentFamily.UNKNOWN;
		private String icon = "";
		private int id = Integer.MIN_VALUE;
		private String infoUrl = "";
		private OperatingSystem operatingSystem;
		private SortedSet<BrowserPattern> patternSet = new TreeSet<BrowserPattern>();
		private String producer = "";
		private String producerUrl = "";
		private BrowserType type;
		private int typeId = Integer.MIN_VALUE;
		private String url = "";

		public Builder() {
			// default constructor
		}

		/**
		 * Creates a new instance of a builder with the data of the passed builder.
		 * 
		 * @param builder
		 *            builder containing the data to be copied
		 * @throws IllegalArgumentException
		 *             if the given argument is {@code null}
		 */
		protected Builder(final Builder builder) {
			if (builder == null) {
				throw new IllegalArgumentException("Argument 'builder' must not be null.");
			}

			this.family = builder.family;
			this.icon = builder.icon;
			this.id = builder.id;
			this.infoUrl = builder.infoUrl;
			this.operatingSystem = builder.operatingSystem;
			this.patternSet = builder.patternSet;
			this.producer = builder.producer;
			this.producerUrl = builder.producerUrl;
			this.type = builder.type;
			this.typeId = builder.typeId;
			this.url = builder.url;
		}

		public Browser build() {
			return new Browser(id, type, family, url, producer, producerUrl, icon, infoUrl, patternSet, operatingSystem);
		}

		/**
		 * Creates a copy (with all its data) of the current builder.
		 * 
		 * @return a new instance of the current builder, never {@code null}
		 */
		public Builder copy() {
			return new Builder(this);
		}

		public UserAgentFamily getFamily() {
			return family;
		}

		public String getIcon() {
			return icon;
		}

		public int getId() {
			return id;
		}

		public String getInfoUrl() {
			return infoUrl;
		}

		public OperatingSystem getOperatingSystem() {
			return operatingSystem;
		}

		public SortedSet<BrowserPattern> getPatternSet() {
			return patternSet;
		}

		public String getProducer() {
			return producer;
		}

		public String getProducerUrl() {
			return producerUrl;
		}

		public BrowserType getType() {
			return type;
		}

		public int getTypeId() {
			return typeId;
		}

		public String getUrl() {
			return url;
		}

		public Builder setFamily(final UserAgentFamily family) {
			if (family == null) {
				throw new IllegalArgumentException("Argument 'family' must not be null.");
			}

			this.family = family;
			return this;
		}

		public Builder setIcon(final String icon) {
			if (icon == null) {
				throw new IllegalArgumentException("Argument 'icon' must not be null.");
			}

			this.icon = icon;
			return this;
		}

		public Builder setId(final int id) {
			if (id < 0) {
				throw new IllegalArgumentException("Argument 'icon' can not be smaller than 0.");
			}

			this.id = id;
			return this;
		}

		public Builder setId(final String id) {
			if (id == null) {
				throw new IllegalArgumentException("Argument 'icon' must not be null.");
			}

			this.setId(Integer.parseInt(id.trim()));
			return this;
		}

		public Builder setInfoUrl(final String infoUrl) {
			if (infoUrl == null) {
				throw new IllegalArgumentException("Argument 'infoUrl' must not be null.");
			}

			this.infoUrl = infoUrl;
			return this;
		}

		public Builder setOperatingSystem(final OperatingSystem operatingSystem) {
			if (operatingSystem == null) {
				throw new IllegalArgumentException("Argument 'operatingSystem' must not be null.");
			}

			this.operatingSystem = operatingSystem;
			return this;
		}

		public Builder setPatternSet(final SortedSet<BrowserPattern> patternSet) {
			if (patternSet == null) {
				throw new IllegalArgumentException("Argument 'patternSet' must not be null.");
			}

			this.patternSet = patternSet;
			return this;
		}

		public Builder setProducer(final String producer) {
			if (producer == null) {
				throw new IllegalArgumentException("Argument 'producer' must not be null.");
			}

			this.producer = producer;
			return this;
		}

		public Builder setProducerUrl(final String producerUrl) {
			if (producerUrl == null) {
				throw new IllegalArgumentException("Argument 'producerUrl' must not be null.");
			}

			this.producerUrl = producerUrl;
			return this;
		}

		/**
		 * Sets the browser type.<br>
		 * <br>
		 * If the given type id is {@code null}, a {@code IllegalArgumentException} will be thrown.
		 * 
		 * @param type
		 *            A browser type
		 */
		public Builder setType(final BrowserType type) {
			if (type == null) {
				throw new IllegalArgumentException("Argument 'type' must not be null.");
			}

			this.type = type;
			return this;
		}

		public Builder setTypeId(final int typeId) {
			if (typeId < 0) {
				throw new IllegalArgumentException("Argument 'typeId' can not be smaller than 0.");
			}

			this.typeId = typeId;
			return this;
		}

		public Builder setTypeId(final String typeId) {
			if (typeId == null) {
				throw new IllegalArgumentException("Argument 'typeId' must not be null.");
			}

			setTypeId(Integer.parseInt(typeId.trim()));
			return this;
		}

		public Builder setUrl(final String url) {
			if (url == null) {
				throw new IllegalArgumentException("Argument 'url' must not be null.");
			}

			this.url = url;
			return this;
		}

	}

	private final UserAgentFamily family;
	private final String icon;
	private final int id;
	private final String infoUrl;
	private final OperatingSystem operatingSystem;
	private final SortedSet<BrowserPattern> patternSet;
	private final String producer;
	private final String producerUrl;
	private final BrowserType type;
	private final String url;

	public Browser(final int id, final BrowserType type, final UserAgentFamily family, final String url, final String producer,
			final String producerUrl, final String icon, final String infoUrl, final SortedSet<BrowserPattern> patternSet,
			final OperatingSystem operatingSystem) {

		if (family == null) {
			throw new IllegalArgumentException("Argument 'family' must not be null.");
		}
		if (icon == null) {
			throw new IllegalArgumentException("Argument 'icon' must not be null.");
		}
		if (id < 0) {
			throw new IllegalArgumentException("Argument 'id' must not be smaller than 0.");
		}
		if (infoUrl == null) {
			throw new IllegalArgumentException("Argument 'infoUrl' must not be null.");
		}
		if (patternSet == null) {
			throw new IllegalArgumentException("Argument 'patternSet' must not be null.");
		}
		if (producer == null) {
			throw new IllegalArgumentException("Argument 'producer' must not be null.");
		}
		if (producerUrl == null) {
			throw new IllegalArgumentException("Argument 'producerUrl' must not be null.");
		}
		if (type == null) {
			throw new IllegalArgumentException("Argument 'type' must not be null.");
		}
		if (url == null) {
			throw new IllegalArgumentException("Argument 'url' must not be null.");
		}

		this.family = family;
		this.icon = icon;
		this.id = id;
		this.infoUrl = infoUrl;
		this.operatingSystem = operatingSystem;
		this.patternSet = patternSet;
		this.producer = producer;
		this.producerUrl = producerUrl;
		this.type = type;
		this.url = url;
	}

	/**
	 * Copy values from itself to a <code>UserAgentInfo.Builder</code>.
	 */
	public void copyTo(final UserAgent.Builder builder) {
		builder.setFamily(family);
		builder.setName(family.getName());
		builder.setProducer(producer);
		builder.setProducerUrl(producerUrl);
		builder.setTypeName(type.getName());
		builder.setUrl(url);
		if (operatingSystem != null) {
			operatingSystem.copyTo(builder);
		}
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
		final Browser other = (Browser) obj;
		if (!family.equals(other.family)) {
			return false;
		}
		if (!icon.equals(other.icon)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (!infoUrl.equals(other.infoUrl)) {
			return false;
		}
		if (operatingSystem == null) {
			if (other.operatingSystem != null) {
				return false;
			}
		} else if (!operatingSystem.equals(other.operatingSystem)) {
			return false;
		}
		if (!patternSet.equals(other.patternSet)) {
			return false;
		}
		if (!producer.equals(other.producer)) {
			return false;
		}
		if (!producerUrl.equals(other.producerUrl)) {
			return false;
		}
		if (!type.equals(other.type)) {
			return false;
		}
		if (!url.equals(other.url)) {
			return false;
		}
		return true;
	}

	public UserAgentFamily getFamily() {
		return family;
	}

	public String getIcon() {
		return icon;
	}

	public int getId() {
		return id;
	}

	public String getInfoUrl() {
		return infoUrl;
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public SortedSet<BrowserPattern> getPatternSet() {
		return patternSet;
	}

	public String getProducer() {
		return producer;
	}

	public String getProducerUrl() {
		return producerUrl;
	}

	public BrowserType getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + family.hashCode();
		result = prime * result + icon.hashCode();
		result = prime * result + id;
		result = prime * result + infoUrl.hashCode();
		result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
		result = prime * result + patternSet.hashCode();
		result = prime * result + producer.hashCode();
		result = prime * result + producerUrl.hashCode();
		result = prime * result + type.hashCode();
		result = prime * result + url.hashCode();
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Browser [family=");
		builder.append(family);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", id=");
		builder.append(id);
		builder.append(", infoUrl=");
		builder.append(infoUrl);
		builder.append(", operatingSystem=");
		builder.append(operatingSystem);
		builder.append(", patternSet=");
		builder.append(patternSet);
		builder.append(", producer=");
		builder.append(producer);
		builder.append(", producerUrl=");
		builder.append(producerUrl);
		builder.append(", type=");
		builder.append(type);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

}
