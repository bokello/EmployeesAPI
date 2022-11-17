package com.springboot.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeApplicationTests {

	
	@Test
	public void testGetEmployeeList() 
	{
				
		try
		{			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8086/api/employees", String.class);
			HttpStatus statusCode = response.getStatusCode();
			Assertions.assertEquals(200, statusCode.value());
			 
		}
		catch(HttpClientErrorException ex)
		{			
			 //Verify bad request and missing header
	         Assertions.assertEquals(400, ex.getRawStatusCode());
	         Assertions.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));		
		}		
	}
	
}
