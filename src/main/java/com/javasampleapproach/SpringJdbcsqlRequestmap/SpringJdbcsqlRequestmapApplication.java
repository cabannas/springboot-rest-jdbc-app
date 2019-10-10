package com.javasampleapproach.SpringJdbcsqlRequestmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase main del proyecto encargada de lanzar la aplicacion de Spring
 * 
 * @author dcabanas
 * @version 30/08/19
 * @see <a href =
 *      "https://grokonez.com/spring-framework/spring-boot/spring-framework-4-3-new-feature-requestmapping-getmapping-postmapping-putmapping-deletemapping"
 *      /> Spring Framework 4.3 New Feature
 *      RequestMapping: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping</a>
 */
@SpringBootApplication
public class SpringJdbcsqlRequestmapApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringJdbcsqlRequestmapApplication.class, args);
	}
}
