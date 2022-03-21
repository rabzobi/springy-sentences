package garland.co.za.springy;

import java.util.logging.Logger;

/**
 * Sentence Tools - Validate a sentence according to specific rules below
 * 
 * @author Robert
 */
public class Sentence {

	// Logger for some debug messages
	private final static Logger logger = Logger.getLogger(Sentence.class.getName());

	/** 
	 * <pre>	
	 * Validate a sentence with the following rules:
	 * 
	 * 1. String starts with a capital letter. 
	 * 2. String has an even number of quotation marks. 
	 * 3. String ends with one of the following sentence termination characters: . ! ? 
	 * 4. String has no period characters other than the last character. 
	 * 5. Numbers below 13 are spelled out (”one”, “two”,"three”...
	 * </pre>
	 * @param sentence: the string to validate
	 * @return true if sentence is valid
	 */
	public static boolean checkValidity(String sentence) {

		// Extra check, need to ensure string length so chatAt functions work
		if (sentence == null || sentence.length() < 1) {
			logger.info("Invalid: Empty string");
			return false;
		}

		if (sentence.charAt(0) != Character.toUpperCase(sentence.charAt(0))) {
			logger.info("Invalid: First char not uppercase");
			return false;
		}

		// call the internal method, see below..
		if (!checkQuotes(sentence)) {
			logger.info("Invalid: Odd number of quotes found");
			return false;
		}

		char lastChar = sentence.charAt(sentence.length() - 1);
		// only 3 chars, if there were more we could search an array
		if (lastChar != '.' && lastChar != '!' && lastChar != '?') {
			logger.info("Invalid: Last char not in valid list . ! ?");
			return false;
		}
		
		
		// Look for all periods and ensure if one exists it's at the end
		if (sentence.contains(".") && sentence.indexOf('.')!=sentence.length()-1) {
			logger.info("FAILURE: Full stop not last char");
			return false;
		}		

		// call the internal check numbers method, see below..
		if (!checkNumbers(sentence)) {
			logger.info("Invalid: Number check");
			return false;
		}

		// if all the checks are not false, we return true
		return true;
	}

	/**
	 * Internal method to check if string contains even quotes. Note: This check
	 * only works on double quotes " not single '
	 * 
	 * @param sentence: string to check
	 * @return true when there are even number of quotes
	 */
	private static boolean checkQuotes(String sentence) {

		// count only double quotation marks
		int quoteMarkCount = 0;
		for (int i = 0; i < sentence.length(); i++) {
			if (sentence.charAt(i) == '"') {
				quoteMarkCount++;
			}
		}

		// if remainder of mod 2 is 1 we are uneven
		if ((quoteMarkCount % 2) == 1) {
			return false;
		}
		return true;
	}

	/**
	 * Internal method to check that all numbers under 13 are spelled out
	 * 
	 * @param sentence: String to check
	 * @return true if all numbers under 13 are spelled out
	 */
	private static boolean checkNumbers(String sentence) {
		for (int i = 0; i < sentence.length(); i++) {
			if (Character.isDigit(sentence.charAt(i))) {
				// when we find a digit iterate until number is finished
				String number = "";
				while (Character.isDigit(sentence.charAt(i))) {
					number += "" + (sentence.charAt(i));
					i++;
				}
				// check if the number we have built in less than 13
				if (Integer.parseInt(number) < 13) {
					return false;
				}
			}
		}
		// if we get here sentence is ok as per rules
		return true;
	}
}
