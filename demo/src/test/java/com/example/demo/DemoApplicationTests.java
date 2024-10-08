package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemoApplicationTests {
	private final String input = "{\n" + //
				"    \"fruits\": {\n" + //
				"        \"apple\": {\n" + //
				"            \"green\": 5,\n" + //
				"            \"red\": 4\n" + //
				"        },\n" + //
				"        \"pear\": {\n" + //
				"            \"green\": 2,\n" + //
				"            \"light green\": 4,\n" + //
				"            \"dark green\": 8\n" + //
				"        },\n" + //
				"        \"lemon\": {\n" + //
				"            \"yellow\": 3,\n" + //
				"            \"green\": 12            \n" + //
				"        }\n" + //
				"    },\n" + //
				"    \"vegetables\": {\n" + //
				"        \"carrot\": {\n" + //
				"            \"orange\": 12,\n" + //
				"            \"red\": 21\n" + //
				"        },\n" + //
				"        \"cabbage\": {\n" + //
				"            \"green\": 8,\n" + //
				"            \"dark green\": 0\n" + //
				"        },\n" + //
				"        \"leek\": {\n" + //
				"            \"green\": 9,\n" + //
				"            \"light green\": 20\n" + //
				"        }\n" + //
				"    }\n" + //
				"}\n" + //
				"";
	@Test
	public void testPrint() {
        String expected = "fruits\n" + //
						".. apple\n" + //
						".... green\n" + //
						".... red\n" + //
						".. pear\n" + //
						".... green\n" + //
						".... light green\n" + //
						".... dark green\n" + //
						".. lemon\n" + //
						".... yellow\n" + //
						".... green\n" + //
						"vegetables\n" + //
						".. carrot\n" + //
						".... orange\n" + //
						".... red\n" + //
						".. cabbage\n" + //
						".... green\n" + //
						".... dark green\n" + //
						".. leek\n" + //
						".... green\n" + //
						".... light green\n";
		String actual = DemoApplication.print(input);
        Assertions.assertEquals(expected, actual);
    }

	@Test
	public void testFindMax() {
        String expected = "vegetables -> carrot -> red: 21";
		String actual = DemoApplication.findMax(input);
        Assertions.assertEquals(expected, actual);
    }
}
