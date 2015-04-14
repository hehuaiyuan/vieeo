package org.vieeo.test.component.other;

import static org.junit.Assert.*;

import org.junit.Test;

class TestError {

	@Test
	public void test() {
		BufferedWriter writer = new File("D:\\export.csv").newWriter('utf-8');

		/*ErrorCodeEnums1.values().each { err ->
			writer.writeLine(err.code+","+err.displayName);
		}*/

		/*writer.writeLine("");
		ErrorCodeAppEnums.values().each { err ->
			writer.writeLine(err.value+","+err.desc);
		}*/

		writer.writeLine("");
		ErrorCodeEnums.values().each { err ->
			writer.writeLine(err.code+","+err.displayName);
		}

		writer.flush();
		writer.close();
	}

}
