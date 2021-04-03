package ch.zhaw.iwi.devops.service.interactionstep;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

public class InteractionStepKey {

	private static final String SEPARATOR = "-----";
	private static final Logger logger = LoggerFactory.getLogger(InteractionStepKey.class);
	public final static String NAME = "nextInteractionStepKey";

	private Long nextInteractionStepKey;
	private Long questionnaireResponseKey;
	private Long interactionElementKey;
	private String interactionElementDisplayValue;
	private String interactionElementExportValue;
	private Map<String, String> customValues = new HashMap<>();

	public InteractionStepKey(Long nextInteractionStepKey, Long questionnaireResponseKey, Long interactionElementKey, String interactionElementDisplayValue) {
		super();
		this.nextInteractionStepKey = nextInteractionStepKey;
		this.questionnaireResponseKey = questionnaireResponseKey;
		this.interactionElementKey = interactionElementKey;
		this.interactionElementDisplayValue = interactionElementDisplayValue;
	}

	public Long getNextInteractionStepKey() {
		return nextInteractionStepKey;
	}

	public void setNextInteractionStepKey(Long nextInteractionStepKey) {
		this.nextInteractionStepKey = nextInteractionStepKey;
	}

	public Long getQuestionnaireResponseKey() {
		return questionnaireResponseKey;
	}

	public void setQuestionnaireResponseKey(Long questionnaireResponseKey) {
		this.questionnaireResponseKey = questionnaireResponseKey;
	}

	public Long getInteractionElementKey() {
		return interactionElementKey;
	}

	public void setInteractionElementKey(Long interactionElementKey) {
		this.interactionElementKey = interactionElementKey;
	}

	public String getInteractionElementDisplayValue() {
		return interactionElementDisplayValue;
	}
	
	public void setInteractionElementDisplayValue(String interactionElementDisplayValue) {
		this.interactionElementDisplayValue = interactionElementDisplayValue;
	}
	
	public String getInteractionElementExportValue() {
		return interactionElementExportValue;
	}
	
	public void setInteractionElementExportValue(String interactionElementExportValue) {
		this.interactionElementExportValue = interactionElementExportValue;
	}
				
	public void addCustomValue(String key, String value) {
		customValues.put(key, value);
	}

	public String getCustomValue(String key) {
		return customValues.get(key);
	}

	public String toEncodedKey() {
		StringBuilder builder = new StringBuilder();
		builder.append("nextInteractionStepKey");
		builder.append(SEPARATOR);
		builder.append(nextInteractionStepKey);
		builder.append(SEPARATOR);
		builder.append("questionnaireResponseKey");
		builder.append(SEPARATOR);
		builder.append(questionnaireResponseKey);
		builder.append(SEPARATOR);
		builder.append("interactionElementKey");
		builder.append(SEPARATOR);
		builder.append(interactionElementKey);
		builder.append(SEPARATOR);
		builder.append(interactionElementDisplayValue);
		builder.append(SEPARATOR);
		builder.append(interactionElementExportValue);
		builder.append(SEPARATOR);
		builder.append(customValues.size());
		for (String key : customValues.keySet()) {
			builder.append(SEPARATOR);
			builder.append(key);
			builder.append(SEPARATOR);
			builder.append(customValues.get(key));
		}

		// encode utf-8
		String encodedString = null;
		try {
			encodedString = URLEncoder.encode(builder.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Cannot encode key", e);
		}
		return encodedString;
	}

	public static InteractionStepKey fromEncodedKey(String string) {
		if (string == null || string.length() <= 0 || ":nextInteractionStepKey".equals(string)) {
			return null;
		}
		try {
			string = URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Cannot decode key", e);
		}
		InteractionStepKey key = new InteractionStepKey(null, null, null, null);
		String[] parts = string.split(SEPARATOR);
		key.setNextInteractionStepKey(Longs.tryParse(parts[1]));
		key.setQuestionnaireResponseKey(Longs.tryParse(parts[3]));
		key.setInteractionElementKey(Longs.tryParse(parts[5]));
		key.setInteractionElementDisplayValue(parts[6]);
		key.setInteractionElementExportValue(parts[7]);
		int customValueCount = Ints.tryParse(parts[8]) * 2;
		int firstElement = 9;
		for (int k = 0; k < customValueCount; k += 2) {
			key.customValues.put(parts[firstElement + k], parts[firstElement + k + 1]);
		}
		return key;
	}

}
