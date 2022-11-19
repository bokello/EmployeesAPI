package com.springboot.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeApplicationTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void checkAPIStatus() throws Exception
	{
		
		 ResultMatcher ok = MockMvcResultMatchers.status()
                 .isOk();
		 
		 MockHttpServletRequestBuilder builder =
                 MockMvcRequestBuilders.get("http://localhost:8086/api/");
           //                            .contentType(MediaType.APPLICATION_JSON)
           //                            .content("Employee API is Up and Running !!"); 
                                       
           this.mockMvc.perform(builder)
           .andExpect(MockMvcResultMatchers.content().string("Employee API is Up and Running !!"))
           .andExpect(ok);
        
	}
		
	
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
