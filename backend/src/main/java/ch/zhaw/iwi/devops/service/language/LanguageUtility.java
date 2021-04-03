package ch.zhaw.iwi.devops.service.language;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;

public class LanguageUtility {

	private static Language english;
	private static Language german;
	private static Language italian;
	private static Language dutch;

	private static ThreadLocal<Language> userLanguage = new ThreadLocal<Language>() {
		@Override
		protected Language initialValue() {
			return null;
		}
	};

	static {
		english = new Language();
		english.setCode("en");
		german = new Language();
		german.setCode("de");
		italian = new Language();
		italian.setCode("it");
		dutch = new Language();
		dutch.setCode("nl");

		english.getName().put(english, "English");
		english.getName().put(german, "Englisch");
		german.getName().put(english, "German");
		german.getName().put(german, "Deutsch");
		italian.getName().put(english, "Italian");
		italian.getName().put(german, "Italienisch");
		dutch.getName().put(english, "Dutch");
		dutch.getName().put(german, "Holländisch");
	}

	private LanguageUtility() {
	}

	public static void setUserLanguage(String languageCode) {
		userLanguage.set(english);
		if (languageCode != null) {
			if (languageCode.equals("de")) {
				userLanguage.set(german);
			} else if (languageCode.equals("it")) {
				userLanguage.set(italian);
			} else if (languageCode.equals("nl")) {
				userLanguage.set(dutch);
			}
		}
	}

	public static Language getUserLanguage() {
		if (userLanguage.get() != null) {
			return userLanguage.get();
		}
		return german; // default
	}
	
	public static Language getLanguage(String languageCode) {
		if (languageCode != null) {
			if (languageCode.equals("de")) {
				return german;
			} else if (languageCode.equals("it")) {
				return italian;
			} else if (languageCode.equals("nl")) {
				return dutch;
			}
		}
		return german;
	}

	public static Language getEnglish() {
		return english;
	}

	public static Language getGerman() {
		return german;
	}

	public static Language getItalian() {
		return italian;
	}

	public static Language getDutch() {
		return dutch;
	}

	public static String getTranslation(Map<Language, String> translations) {
		if (translations == null || translations.isEmpty()) {
			return "";
		}
		if (Strings.isNullOrEmpty(translations.get(getUserLanguage()))) {
			for (String translation : translations.values()) {
				if (!Strings.isNullOrEmpty(translation)) {
					return translation;
				}
			}
		}
		return translations.get(getUserLanguage());
	}
	
	public static Map<Language, String> createLanguageMap(String name) {
		Map<Language, String> map = new HashMap<>();
		map.put(LanguageUtility.getGerman(), name);
		map.put(LanguageUtility.getEnglish(), name);
		map.put(LanguageUtility.getItalian(), name);
		map.put(LanguageUtility.getDutch(), name);
		return map;
	}
	
}
