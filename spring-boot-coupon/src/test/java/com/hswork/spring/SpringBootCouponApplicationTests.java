package com.hswork.spring;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.hswork.spring.domain.Coupon;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootCouponApplicationTests {
	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType()
			, MediaType.APPLICATION_JSON.getSubtype()
			, Charset.forName("utf8"));
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).
				stream().filter(
					converter -> converter instanceof MappingJackson2HttpMessageConverter).
					findAny().get();
	}
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void C_메일_중복검증() throws Exception {
		// 동일한 메일 등록 재요청 
		Coupon coupon = new Coupon("user@test.com");
		coupon.setCreated(null);
		
		mockMvc.perform(post("/coupons")
				.content(this.toJsonString(coupon))
				.contentType(contentType)).andExpect(status().is4xxClientError());	// 409오류 리턴
	}
	
	@Test
	public void B_쿠폰목록조회_GET_COUPONS() throws Exception {
		mockMvc.perform(get("/coupons"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))			// ContentType 검증(JSON)
				.andExpect(jsonPath("$['totalElements']", is(123)))		// 초기 세팅 데이터 122건 + JUnit 등록 1건 
				.andExpect(jsonPath("$['content'][0]['email']", containsString("user@test.com")));
	}
	
	@Test
	public void A_쿠폰등록_POST_COUPONS() throws Exception {
		Coupon coupon = new Coupon("user@test.com");
		coupon.setCreated(null);
		
		mockMvc.perform(post("/coupons")
				.content(this.toJsonString(coupon))
				.contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType));			// ContentType 검증(JSON)

	}
	
	@SuppressWarnings("unchecked")
	protected String toJsonString(Object obj) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
