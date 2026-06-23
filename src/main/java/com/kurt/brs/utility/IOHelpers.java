package com.kurt.brs.utility;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class IOHelpers {

	@SuppressWarnings("deprecation")
	public static void printHTML(String html, String pageName) {
		FileWriter fw;
		try {
			fw = new FileWriter(System.getenv("APPDATA") + "\\" + pageName
					+ ".html");
			fw.write(html);
			fw.close();
			Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler file:///"
							+ System.getenv("APPDATA") + "/" + pageName
							+ ".html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getFileAsString(String filePath) {
		String content = new String("");
		try {
			InputStream in = IOHelpers.class.getClassLoader().getResourceAsStream(
					filePath);
			BufferedReader input = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

			String line;
			while ((line = input.readLine()) != null) {
				content = content + line;
			}
			input.close();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return content;
	}
}
