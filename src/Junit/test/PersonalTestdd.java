package Junit.test;

import java.io.IOException;

import org.junit.Test;

public class PersonalTestdd {

	@Test
	public void test() throws IOException {
		String command = "cmd /c netsh wlan start hostednetwork";
		Runtime.getRuntime().exec(command);
	}
}
