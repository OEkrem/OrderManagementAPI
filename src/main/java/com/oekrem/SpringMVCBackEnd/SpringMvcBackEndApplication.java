package com.oekrem.SpringMVCBackEnd;

import com.oekrem.SpringMVCBackEnd.Dto.Mapper.UserMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvcBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcBackEndApplication.class, args);
	}

}
