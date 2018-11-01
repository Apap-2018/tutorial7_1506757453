package com.apap.tutorial7.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.rest.Setting;

@RestController
@RequestMapping("/airport")
public class AirportController {
	
	/*
	 * Cari airport berdasarkan nama kota, jadi term nya itu kita bisa masukin nama2 kota
	 */
	@GetMapping()
    private String airportList(@RequestParam("namaKota") String namaKota) throws Exception {
		RestTemplate rest = new RestTemplate();
		String path = Setting.airportUrl+"&term="+namaKota+"&country=ID";
    	String airports = rest.getForEntity(path, String.class).getBody();
    	return airports;
    }
}
