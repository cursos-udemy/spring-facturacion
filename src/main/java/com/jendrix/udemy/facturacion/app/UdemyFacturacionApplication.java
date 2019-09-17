package com.jendrix.udemy.facturacion.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.jendrix.udemy.facturacion.app.model.service.UploadFileService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class UdemyFacturacionApplication implements CommandLineRunner {

	@Autowired
	private UploadFileService uploadFileService;

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(UdemyFacturacionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Configuracion inicial de la aplicacion");
		log.info("Elimando archivos temporales ...");
		this.uploadFileService.deleteAll();
		log.info("Preparando upload folder ...");
		this.uploadFileService.init();
		log.info("Inicializacion de la aplicacion OK");
	}

}
