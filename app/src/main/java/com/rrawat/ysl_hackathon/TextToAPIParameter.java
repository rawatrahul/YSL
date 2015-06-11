package com.rrawat.ysl_hackathon;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextToAPIParameter {

	public static final String ACCOUNT_ID = "ACCOUNT";
	public static final String TRANSACTION_ID = "TRANSACTION";
	public static final String HOLDING_ID = "HOLDING";

	private static final String BANK_CONTAINER = "bank";
	private static final String CARD_CONTAINER = "card";
	private static final String INV_CONTAINER1 = "investment";
	private static final String INV_CONTAINER2 = "stock";
	private static final String LOAN_CONTAINER = "loan";
	private static final String MORTGAGE_CONTAINER = "mortgage";
	private static final String BILL_CONTAINER = "bill";

	public static final String RET_PARAM_METHOD_NAME = "METHOD";
	public static final String RET_PARAM_CONTAINER_NAME = "CONTAINER";
	public static final String RET_PARAM_MAGNITUDE = "MAGNITUDE";
	public static final String RET_PARAM_REST_URL = "REST_URL";

	private static final HashMap<String, String> levelKeyword = new HashMap<String, String>();
	private static final HashMap<String, Integer> containerKeyword = new HashMap<String, Integer>();
	private static final HashMap<String, String> containerRestUrlMap = new HashMap<String, String>();

	private static final String ACCOUNT_LEVEL = "account";
	private static final String HOLDING_LEVEL = "holding";
	private static final String SECURITY_LEVEL = "security";
	private static final String SECURITIES_LEVEL = "securities";
	private static final String TRANSACTION_LEVEL = "transaction";
	private static final String HISTORY_LEVEL = "history";

	static {
		levelKeyword.put(ACCOUNT_LEVEL, ACCOUNT_ID);
		levelKeyword.put(HOLDING_LEVEL, HOLDING_ID);
		levelKeyword.put(SECURITY_LEVEL, HOLDING_ID);
		levelKeyword.put(SECURITIES_LEVEL, HOLDING_ID);
		levelKeyword.put(TRANSACTION_LEVEL, TRANSACTION_ID);
		levelKeyword.put(HISTORY_LEVEL, TRANSACTION_ID);

		containerRestUrlMap.put(ACCOUNT_ID, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/accounts/v1");
		containerRestUrlMap.put(TRANSACTION_ID, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/transactions/v1");
		containerRestUrlMap.put(HOLDING_ID, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/holdings/v1");
	}

	/**
	 * Get 5 bank Transactions
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getAPIParametersFromSpokenText(String text) {
		HashMap<String, String> methodcallParameterMap = new HashMap<String, String>();


		String magnitude = getMagnitude(text);
		String container = getContainer(text);
		String level = getLevel(text);

		methodcallParameterMap.put(RET_PARAM_METHOD_NAME, level);
		methodcallParameterMap.put(RET_PARAM_CONTAINER_NAME, container);
		methodcallParameterMap.put(RET_PARAM_MAGNITUDE, magnitude);

		getRestUrl(methodcallParameterMap);

		return methodcallParameterMap;
	}


	private void getRestUrl(HashMap<String, String> apiParamMap) {

		String urlString = containerRestUrlMap.get(apiParamMap.get(RET_PARAM_METHOD_NAME));
		String container = apiParamMap.get(RET_PARAM_CONTAINER_NAME);

		if(container != null) {
			urlString += "?container="+container;
		}
		apiParamMap.put(RET_PARAM_REST_URL, urlString);
	}

	private String getLevel(String text) {

		String level = null;
		Set<String> keySet = levelKeyword.keySet();

		for (String key : keySet) {
			if(text.toLowerCase().contains(key)) {
				level = levelKeyword.get(key);
				break;
			}
		}

		return level;
	}


	/**
	 * Expectation as of now is that there will be only one number in entire sentence
	 * @param text
	 * @return
	 * @throws Exception
	 */
	private String getMagnitude(String text) {
		String magnitude = "0";
		try {
			Pattern p = Pattern.compile("\\d+");
			Matcher matcher = p.matcher(text);

			if(matcher.find()) {
				magnitude = matcher.group();
			}

		} catch (Exception e) {
			System.out.println("Unable to get the magnitude from speech text");
//			throw new Exception("Unable to get the magnitude from speech text");
		}
		return magnitude;
	}

	private String getContainer(String text) {
		String actualContainerType = null;
		if(text.toLowerCase().contains(BANK_CONTAINER)) {
			actualContainerType = BANK_CONTAINER;
		} else if(text.toLowerCase().contains(CARD_CONTAINER)) {
			actualContainerType = CARD_CONTAINER;
		} else if(text.toLowerCase().contains(INV_CONTAINER1) || text.toLowerCase().contains(INV_CONTAINER2)) {
			actualContainerType = INV_CONTAINER1;
		} else if(text.toLowerCase().contains(BILL_CONTAINER)) {
			actualContainerType = BILL_CONTAINER;
		} else if(text.toLowerCase().contains(LOAN_CONTAINER)) {
			actualContainerType = LOAN_CONTAINER;
		} else if(text.toLowerCase().contains(MORTGAGE_CONTAINER)) {
			actualContainerType = MORTGAGE_CONTAINER;
		}
		return actualContainerType;
	}

	public static void main(String[] args) throws Exception {
		TextToAPIParameter demo = new TextToAPIParameter();
		System.out.println(demo.getAPIParametersFromSpokenText("need 12 securities from my card account"));

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher("There are more than -2 and less than 12 numbers here");
		while (m.find()) {
			System.out.println(m.group());
		}
	}
}
