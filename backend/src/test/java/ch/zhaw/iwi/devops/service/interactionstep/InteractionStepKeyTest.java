package ch.zhaw.iwi.devops.service.interactionstep;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.zhaw.iwi.devops.service.interactionstep.InteractionStepKey;

public class InteractionStepKeyTest {

	@Test
	public void testCustomValues1() throws Exception {
		InteractionStepKey key = new InteractionStepKey(1L, 10L, 100L, "VALUE");
		key.addCustomValue("TEST", "123");
		String encodedKey = key.toEncodedKey();
		key = InteractionStepKey.fromEncodedKey(encodedKey);
		String result = key.getCustomValue("TEST");
		assertEquals("123", result);
	}
	
	@Test
	public void testCustomValues2() throws Exception {
		InteractionStepKey key = new InteractionStepKey(1L, 10L, 100L, "VALUE");
		key.addCustomValue("TEST", "123");
		key.addCustomValue("TEST2", "456");
		key.setInteractionElementExportValue("abc");
		String encodedKey = key.toEncodedKey();
		key = InteractionStepKey.fromEncodedKey(encodedKey);
		String result = key.getCustomValue("TEST");
		assertEquals("123", result);
		result = key.getCustomValue("TEST2");
		assertEquals("456", result);
	}
	
	@Test
	public void testStructuredValue1() throws Exception {
		InteractionStepKey key = new InteractionStepKey(1L, 10L, 100L, "VALUE");
		key.setInteractionElementExportValue("abc");
		String encodedKey = key.toEncodedKey();
		key = InteractionStepKey.fromEncodedKey(encodedKey);
		assertEquals("abc", key.getInteractionElementExportValue());
	}
	
	@Test
	public void testStructuredValue2() throws Exception {
		InteractionStepKey key = new InteractionStepKey(1L, 10L, 100L, "VALUE");
		key.addCustomValue("TEST", "123");
		key.addCustomValue("TEST2", "456");
		key.setInteractionElementExportValue("abc");
		String encodedKey = key.toEncodedKey();
		key = InteractionStepKey.fromEncodedKey(encodedKey);
		assertEquals("abc", key.getInteractionElementExportValue());
	}
	
}
