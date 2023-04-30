package fr.phonetastik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import fr.phonetastik.utils.StorageService;

@SpringBootApplication
@ComponentScan("fr.phonetastik")
public class Phonetastik2Application extends SpringBootServletInitializer  {
	
	//implements CommandLineRunner
	
	@Autowired
	StorageService storageService;
	

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Phonetastik2Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Phonetastik2Application.class, args);
	}

	/*
	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
	*/

}
