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
	private static final String CARD_CONTAINER = "creditCard";
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
	private static final HashMap<String[], String> smartKeywordList = new HashMap<String[], String>();
	private static final HashMap<String, String> containerRestUrlMap = new HashMap<String, String>();

	private static final String ACCOUNT_LEVEL = "account";
	private static final String HOLDING_LEVEL = "holding";
	private static final String SECURITY_LEVEL = "security";
	private static final String SECURITIES_LEVEL = "securities";
	private static final String TRANSACTION_LEVEL = "transaction";
	private static final String HISTORY_LEVEL = "history";
	private static final String ACCOUNT_LEVEL_2 = "details";

	/**
	 * These data can be learnt more by user's usage and update in DB
	 */
	private static final String[] P_KEY_COMBO1 = {"spending", "category"};
	private static final String[] P_KEY_COMBO2 = {"spending", "account"};
	private static final String[] P_KEY_COMBO3 = {"spent", "more", "money"};
	private static final String[] P_KEY_COMBO4 = {"spent", "most"};
	private static final String[] P_KEY_COMBO5 = {"spending", "more", "money"};
	private static final String[] P_KEY_COMBO6 = {"where", "lost", "money"};
	private static final String[] P_KEY_COMBO7 = {"my", "value"};
	private static final String[] P_KEY_COMBO8 = {"my", "networth"};
	private static final String[] P_KEY_COMBO9 = {"my", "worth"};
	private static final String[] P_KEY_COMBO10 = {"profit", "i am"};
	private static final String[] P_KEY_COMBO11 = {"profit", "i'm"};
	private static final String[] P_KEY_COMBO12 = {"spend", "most"};
	private static final String[] P_KEY_COMBO13 = {"am i", "worth"};
	private static final String[] P_KEY_COMBO14 = {"am", "worth"};
	private static final String[] P_KEY_COMBO15 = {"lose", "money"};
	private static final String[] P_KEY_COMBO16 = {"losing", "money"};

	public static final String MAX_EXPENSE_CAT = "MAX_EXPENSE_CAT";
	public static final String NET_WORTH = "NET_WORTH";

	public static final String SMART_KEYWORD = "SMART_KEYWORD";

	static {
		levelKeyword.put(ACCOUNT_LEVEL, ACCOUNT_ID);
		levelKeyword.put(HOLDING_LEVEL, HOLDING_ID);
		levelKeyword.put(SECURITY_LEVEL, HOLDING_ID);
		levelKeyword.put(SECURITIES_LEVEL, HOLDING_ID);
		levelKeyword.put(TRANSACTION_LEVEL, TRANSACTION_ID);
		levelKeyword.put(HISTORY_LEVEL, TRANSACTION_ID);
		levelKeyword.put(ACCOUNT_LEVEL_2, ACCOUNT_ID);

		smartKeywordList.put(P_KEY_COMBO1, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO2, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO3, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO4, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO5, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO6, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO7, NET_WORTH);
		smartKeywordList.put(P_KEY_COMBO8, NET_WORTH);
		smartKeywordList.put(P_KEY_COMBO9, NET_WORTH);
		smartKeywordList.put(P_KEY_COMBO10, NET_WORTH);
		smartKeywordList.put(P_KEY_COMBO11, NET_WORTH);
		smartKeywordList.put(P_KEY_COMBO12, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO13, NET_WORTH);
		smartKeywordList.put(P_KEY_COMBO14, NET_WORTH);
		smartKeywordList.put(P_KEY_COMBO15, MAX_EXPENSE_CAT);
		smartKeywordList.put(P_KEY_COMBO16, MAX_EXPENSE_CAT);

		containerRestUrlMap.put(ACCOUNT_ID, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/accounts/v1");
		containerRestUrlMap.put(TRANSACTION_ID, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/transactions/v1");
		containerRestUrlMap.put(HOLDING_ID, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/holdings/v1");
		containerRestUrlMap.put(MAX_EXPENSE_CAT, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/transactions/v1");
		containerRestUrlMap.put(NET_WORTH, "https://stage.api.yodlee.com/ysl/private-yslsandbox20/accounts/v1");
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

		//In case if smart keyword present then we can simply return this smart keyword
		String smartKeyword = getSmartKeywordsCategory(text);
		if(smartKeyword != null) {
			methodcallParameterMap.put(SMART_KEYWORD, smartKeyword);
			methodcallParameterMap.put(RET_PARAM_METHOD_NAME, smartKeyword);
			getRestUrl(methodcallParameterMap);
			return methodcallParameterMap;
		}

		String magnitude = getMagnitude(text);
		String container = getContainer(text);
		String level = getLevel(text);

		methodcallParameterMap.put(RET_PARAM_METHOD_NAME, level);
		methodcallParameterMap.put(RET_PARAM_CONTAINER_NAME, container);
		methodcallParameterMap.put(RET_PARAM_MAGNITUDE, magnitude);

		getRestUrl(methodcallParameterMap);

		return methodcallParameterMap;
	}



	private String getSmartKeywordsCategory(String text) {
		Set<String[]> smartKeySet = smartKeywordList.keySet();
		String smartKeyword = null;
		int negativeMatchCount;

		for (String[] smartKeys : smartKeySet) {
			negativeMatchCount = 0;

			for (String smartKey : smartKeys) {
				if(!text.toLowerCase().contains(smartKey)) {
					negativeMatchCount++;
				}
			}

			if(negativeMatchCount <= 0) {
				smartKeyword = smartKeywordList.get(smartKeys);
				break;
			}

		}
		return smartKeyword;
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
		} else if(text.toLowerCase().contains(CARD_CONTAINER) || text.toLowerCase().contains("card")) {
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
		System.out.println(demo.getAPIParametersFromSpokenText("Where do i spend most"));

		/*Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher("There are more than -2 and less than 12 numbers here");
		while (m.find()) {
		  System.out.println(m.group());
		}*/



	}
}
