package org.ojug.demos.rest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UserGroupInfoFetchingSimpleDemo1 {

	private String key;

	public UserGroupInfoFetchingSimpleDemo1() {
		// from https://secure.meetup.com/meetup_api/key/
		key = System.getenv("meetup_api_key");
	}

	public static void main(String[] args) {
		try {
			PrintWriter out = new PrintWriter("demo.txt");
			UserGroupInfoFetchingSimpleDemo1 infoFetcher = new UserGroupInfoFetchingSimpleDemo1();
			String[] groups = { "omahajava", "coffeeandcode" };
			for (String groupName : groups) {
				String description = infoFetcher.getDescription(groupName);
				out.println(description);
			}
			out.close();
		} catch (Exception bland) {
			bland.printStackTrace();
		}
		// https://api.meetup.com/omahajava?key=
	}

	private String getDescription(String groupName) {
		String json = getJson(groupName);
		return json; // TODO: Parse the json
	}
	
	private String getJson(String groupName) {
		// TODO: Refactor this!
		String json = null;
		String urlString = String.format("https://api.meetup.com/%s?key=%s", groupName, key);
		try {
			URLConnection conn = new URL(urlString).openConnection();
			LineNumberReader in = new LineNumberReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while (null != (line = in.readLine())) { 
				json += line;
			}
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
