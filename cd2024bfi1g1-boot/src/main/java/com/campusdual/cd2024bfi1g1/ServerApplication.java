package com.campusdual.cd2024bfi1g1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ontimize.jee.server.security.encrypt.IPasswordEncryptHelper;
import com.ontimize.jee.server.security.encrypt.PasswordBCryptHelper;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class ServerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public IPasswordEncryptHelper passwordEncryptHelper() {
		return new PasswordBCryptHelper();
	}
}
