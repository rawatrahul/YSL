package com.rrawat.ysl_hackathon;

import java.util.HashMap;
import java.util.Set;


public class TextToAPIParameter {
	
	private static final String ACCOUNT_ID = "ACCOUNT";
	private static final String TRANSACTION_ID = "TRANSACTION";
	private static final String HOLDING_ID = "HOLDING";
	
	private static final String BANK_CONTAINER = "bank";
	private static final String CARD_CONTAINER = "card";
	private static final String INV_CONTAINER1 = "investment";
	private static final String INV_CONTAINER2 = "stock";
	private static final String LOAN_CONTAINER = "loan";
	private static final String MORTGAGE_CONTAINER = "mortgage";
	private static final String BILL_CONTAINER = "bill";
	
	private static final String RET_PARAM_METHOD_NAME = "METHOD";
	private static final String RET_PARAM_CONTAINER_NAME = "CONTAINER";
	private static final String RET_PARAM_MAGNITUDE = "MAGNITUDE";
	
	private static final HashMap<String, String> levelKeyword = new HashMap<String, String>();
	private static final HashMap<String, Integer> magnitudeKeyword = new HashMap<String, Integer>();
	private static final HashMap<String, Integer> containerKeyword = new HashMap<String, Integer>();
	
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
	}
	
	
	/**
	 * Get Karthik's 5 bank Transactions
	 * 
	 * @param text
	 * @return
	 */
	private HashMap<String, String> getAPIParametersFromSpokenText(String text) {
		HashMap<String, String> methodcallParameterMap = new HashMap<String, String>();
		
		text = removeUnwantedWords(text);
		
		String[] characters = null;
		characters = text.split(" ");
		int retrievalIndex = 0;
		
		String name = characters[retrievalIndex++];
		
		int magnitude = getMagnitude(characters[retrievalIndex]);
		if(magnitude != 0) {
			retrievalIndex++;
		}
		
		String container = getContainer(characters[retrievalIndex]);
		if(container != null) {
			retrievalIndex++;
		}
		
		methodcallParameterMap.put(RET_PARAM_METHOD_NAME, getLevel(characters[retrievalIndex]));
		methodcallParameterMap.put(RET_PARAM_CONTAINER_NAME, container);
		methodcallParameterMap.put(RET_PARAM_MAGNITUDE, magnitude+"");
		
		return methodcallParameterMap;
	}


	private String getLevel(String levelString) {
		
		String level = null;
		Set<String> keySet = levelKeyword.keySet();
		
		for (String key : keySet) {
			if(levelString.toLowerCase().contains(key)) {
				level = levelKeyword.get(key);
				break;
			}
		}
		
		return level;
	}


	private int getMagnitude(String magnitudeStr) {
		int magnitude = 0;
		try {
			magnitude = Integer.parseInt(magnitudeStr);
		} catch (Exception e) {
			System.out.println("Unable to get the magnitude from speech text");
		}
		return magnitude;
	}


	private String removeUnwantedWords(String text) {
		text = text.replace("get ", "").trim();
		text = text.replace("Get ", "").trim();
		text = text.replace("GET ", "").trim();
		text = text.replace("show ", "").trim();
		text = text.replace("Show ", "").trim();
		text = text.replace("SHOW ", "").trim();
		return text;
	}


	private String getContainer(String containerType) {
		String actualContainerType = null;
		if(containerType.toLowerCase().contains(BANK_CONTAINER)) {
			actualContainerType = BANK_CONTAINER;
		} else if(containerType.toLowerCase().contains(CARD_CONTAINER)) {
			actualContainerType = CARD_CONTAINER;
		} else if(containerType.toLowerCase().contains(INV_CONTAINER1) || containerType.toLowerCase().contains(INV_CONTAINER2)) {
			actualContainerType = INV_CONTAINER1;
		} else if(containerType.toLowerCase().contains(BILL_CONTAINER)) {
			actualContainerType = BILL_CONTAINER;
		} else if(containerType.toLowerCase().contains(LOAN_CONTAINER)) {
			actualContainerType = LOAN_CONTAINER;
		} else if(containerType.toLowerCase().contains(MORTGAGE_CONTAINER)) {
			actualContainerType = MORTGAGE_CONTAINER;
		}
		return actualContainerType;
	}
	
	public static void main(String[] args) {
		TextToAPIParameter demo = new TextToAPIParameter();
		System.out.println(demo.getAPIParametersFromSpokenText("Get my bank transactions"));
	}
}
