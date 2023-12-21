package org.sid.customerqueryside;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.sid.customerqueryside.configs.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class CustomerQuerySideApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerQuerySideApplication.class, args);
	}
	@Bean
	public CommandBus commandBus(){
		return SimpleCommandBus.builder().build();
	}
}
