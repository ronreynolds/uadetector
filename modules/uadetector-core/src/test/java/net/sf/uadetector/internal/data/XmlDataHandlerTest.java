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
package net.sf.uadetector.internal.data;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlDataHandlerTest {

	@Test(expected = IllegalArgumentException.class)
	public void constructor_null() {
		new XmlDataHandler(null);
	}

	@Test
	public void resolveEntity_knownUrl() throws IOException, SAXException {
		final XmlDataHandler handler = new XmlDataHandler(new Data.Builder());
		final InputSource input = handler.resolveEntity("publicId is irrelevant", XmlDataHandler.UASDATA_DEF_URL);
		Assert.assertNotNull(input);
	}

	@Test(expected = SAXException.class)
	public void resolveEntity_unknownUrl() throws IOException, SAXException {
		final XmlDataHandler handler = new XmlDataHandler(new Data.Builder());
		handler.resolveEntity("publicId unknown", "systemId unknown");
	}

}
