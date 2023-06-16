package com.swt.fahrradshop.bestellung;


import com.google.gson.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@SpringBootApplication
public class BestellungApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestellungApplication.class, args);
	}
	/*
              Parse the request body that is a String JSON to a String
              from:
              {
                  "kundeId": "Leo"
              }
              to:
              Leo
      */
	public static String JSONStringTOString(String JSONString,String key){
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(JSONString).getAsJsonObject();
		return jsonObject.get(key).getAsString();
	}

}
