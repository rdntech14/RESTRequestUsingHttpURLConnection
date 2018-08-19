import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.MyPOJOforList;
import pojo.MyPOJOforUpdate;

public class Main {

	public static void main(String[] args) throws IOException {
		// how to send Get request and read response as String
		getRequestDemo();

		// how to send POST request and JSON request body is in Java String. Convert
		// String into bytes and send.
		postRequestDemo1();

		// how to send POST request and JSON request body is in .json file. Read .json
		// file and convert into String and String into bytes.
		postRequestDemo2();

		// parse post response using jackson and response is in JSON object
		/*
		 * To parse Response in Java, we may use following libraries 
		 * 1. JSON.simple 
		 * 2.GSON 
		 * 3. Jackson
		 * 
		 * Note : we are going to use Jackson - good for big json file
		 */
		postRequestDemo3();

		// parse get response using jackson and response is in JSON Array
		postRequestDemo4();
	}

	// Get Request Demo
	private static void getRequestDemo() throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL("http://localhost:8080/CreateRESTFulService/rest/employee/list/1002");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// set Request Method
		con.setRequestMethod("GET");

		// Set connection parameters
		con.setRequestProperty("Authorization", "Bearer tokenvalue");
		con.setRequestProperty("Accept", "application/json");
		con.setConnectTimeout(10000);

		if (con.getResponseCode() != 200) {
			throw new RuntimeException();
		}

		// faster
		System.out.println("BufferedReader");
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		br.close();

		// output
		System.out.println(sb);

		// if (con.getResponseCode()!=200) {
		// throw new RuntimeException();
		// }
		//
		// System.out.println("BufferedInputStream");
		// BufferedInputStream bis = new BufferedInputStream((con.getInputStream()));
		//
		// int c;
		// StringBuilder sb1 = new StringBuilder();
		// while((c = bis.read())!=-1) {
		// sb1.append((char) c);
		// }
		// bis.close();

		// output
		// System.out.println(sb1);
		//
	}


	private static void postRequestDemo1() throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL("http://localhost:8080/CreateRESTFulService/rest/employee/update");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// set PUT or POST Request Method
		con.setRequestMethod("PUT");
		// con.setRequestMethod("POST");

		// Set connection parameters
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer tokenvalue");
		con.setRequestProperty("Content-Type", "application/json");
		con.setConnectTimeout(10000);

		// to send json block as String
		con.setDoOutput(true);
		String body = "{\n" + "        \"firstName\": \"first111\",\n" + "        \"id\": 1001,\n"
				+ "        \"lastName\": \"last11111111\"\n" + "    }";
		con.getOutputStream().write(body.getBytes());

		// Reading response Result
		if (con.getResponseCode() != 200) {
			throw new RuntimeException();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		System.out.println(sb);

	}


	private static void postRequestDemo2() throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL("http://localhost:8080/CreateRESTFulService/rest/employee/update");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// set PUT or POST Request Method
		con.setRequestMethod("PUT");
		// con.setRequestMethod("POST");

		// Set connection parameters
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer tokenvalue");
		con.setRequestProperty("Content-Type", "application/json");
		con.setConnectTimeout(10000);

		// to send json block as String
		con.setDoOutput(true);

		BufferedReader br1 = new BufferedReader(
				new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/jsonblockforpost.json")));

		String s1;
		StringBuilder sb1 = new StringBuilder();
		while ((s1 = br1.readLine()) != null) {
			sb1.append(s1);
		}
		System.out.println(sb1);

		con.getOutputStream().write(sb1.toString().getBytes());

		// Reading response Result
		if (con.getResponseCode() != 200) {
			throw new RuntimeException();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		System.out.println(sb);

		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir") + "/output.json")));
		bw.write(sb.toString());
		bw.close();
	}

	private static void postRequestDemo3() throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL("http://localhost:8080/CreateRESTFulService/rest/employee/update");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// set PUT or POST Request Method
		con.setRequestMethod("PUT");
		// con.setRequestMethod("POST");

		// Set connection parameters
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer tokenvalue");
		con.setRequestProperty("Content-Type", "application/json");
		con.setConnectTimeout(10000);

		// to send json block as String
		con.setDoOutput(true);
		String body = "{\n" + "        \"firstName\": \"first111\",\n" + "        \"id\": 1001,\n"
				+ "        \"lastName\": \"last11111111\"\n" + "    }";

		con.getOutputStream().write(body.getBytes());

		// Reading response Result
		if (con.getResponseCode() != 200) {
			throw new RuntimeException();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		System.out.println(sb);

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		// convert json string to object
		MyPOJOforUpdate myPOJO = objectMapper.readValue(sb.toString().getBytes(), MyPOJOforUpdate.class);

		System.out.println(myPOJO.getMessage());

	}

	private static void postRequestDemo4() throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL("http://localhost:8080/CreateRESTFulService/rest/employee/list");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// set PUT or POST Request Method
		con.setRequestMethod("GET");

		// Set connection parameters
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer tokenvalue");
		con.setConnectTimeout(10000);

		// Reading response Result
		if (con.getResponseCode() != 200) {
			throw new RuntimeException();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		System.out.println(sb);

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		// convert json string to object
		MyPOJOforList[] myPOJO = objectMapper.readValue(sb.toString().getBytes(), MyPOJOforList[].class);

		System.out.println(myPOJO[1].getFirstName());

	}

}
