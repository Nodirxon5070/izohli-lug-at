package com.company.Izohli.lug.at;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		tags = @Tag(name = "Main tag"),
		info = @Info(
				version = "v: 3.0.2",
				title = "Izohli lug'at Project",
				contact = @Contact(
						name = "Izohli lug'at Project",
						url = "https://t.me/xan_5070",
						email = "sayfullayev.n070@gmail.com"
				),
				license = @License(
						name = "Izohli lug'at Project",
						url = "https://t.me/xan_5070"
				)
		),
		servers = {@Server(url = "http://localhost:8091")}
)
public class IzohliLugatMonolitApplication {

	public static void main(String[] args) {
		SpringApplication.run(IzohliLugatMonolitApplication.class, args);
	}

}
