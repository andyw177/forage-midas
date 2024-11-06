package com.jpmc.midascore.component;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.midascore.foundation.Balance;

@RestController
public class MidasController {
	private final DatabaseConduit services;
 	public MidasController(DatabaseConduit services) {
        this.services = services;
    }
	@GetMapping("/balance")
	public Balance getBalance(@RequestParam(value = "userId") int id) {
		return services.getUserBalance(id);
		
	}

}
