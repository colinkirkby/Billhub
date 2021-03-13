package com.Spring.BillHub;
import com.Spring.BillHub.dto.UserRegistrationDto;
import com.google.gson.Gson;
import com.Spring.BillHub.Controller.UserController;
import com.Spring.BillHub.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class BillHubApplicationTests {

	@Autowired
	private UserController controller;



	@Test
	public void contextLoads() throws Exception{
		assert(controller != null);
	}


	@Autowired
	private MockMvc mockMvc;
/** this should only can be ran once to add the test user to the database it will fail after because the test user is already in the database
	@Test
	public void shouldAddNewUser() throws Exception{
		UserRegistrationDto test = new UserRegistrationDto("test", "test", "test", "test");
		Gson gson = new Gson();
		String json = gson.toJson(test);
		this.mockMvc.perform(post("/api/v1/registration").contentType(MediaType.APPLICATION_JSON).content(json));
	}*/

	@Test
	public void shouldAddNewTransaction() throws Exception {
		String[] strings = {"test", "test", "test", "test", "test"};
		Gson gson = new Gson();
		String json = gson.toJson(strings);
		System.out.println(json);
		this.mockMvc.perform(post("/api/v1/newtrans").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}
	@Test void shouldfailLoginUserDoesNotExist() throws Exception{
		UserRegistrationDto test = new UserRegistrationDto("shouldfail", "test", "shouldFail", "test");
		Gson gson = new Gson();
		String json = gson.toJson(test);
		this.mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(content().string(containsString("login-failed, invalid username")));
	}

	@Test void shouldPassLogin() throws Exception{
		UserRegistrationDto test = new UserRegistrationDto("test", "test", "test", "test");
		Gson gson = new Gson();
		String json = gson.toJson(test);
		this.mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(content().string(containsString("Success")));
	}




}
