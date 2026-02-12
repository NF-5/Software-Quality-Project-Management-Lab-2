package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

	/**
	 * Test multiplication: 110 (6) * 101 (5) = 11110 (30)
	 */
	@Test
	public void multiply() throws Exception {
		this.mvc.perform(get("/multiply").param("operand1","110").param("operand2","101"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("11110"));
	}

	/**
	 * Test multiplication with JSON response: 111 (7) * 10 (2) = 1110 (14)
	 */
	@Test
	public void multiplyJson() throws Exception {
		this.mvc.perform(get("/multiply_json").param("operand1","111").param("operand2","10"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(10))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1110))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
	}

	/**
	 * Test bitwise AND: 1111 & 1010 = 1010
	 */
	@Test
	public void bitwiseAnd() throws Exception {
		this.mvc.perform(get("/and").param("operand1","1111").param("operand2","1010"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1010"));
	}

	/**
	 * Test bitwise AND with JSON response: 1101 & 1011 = 1001
	 */
	@Test
	public void bitwiseAndJson() throws Exception {
		this.mvc.perform(get("/and_json").param("operand1","1101").param("operand2","1011"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1101))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1011))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
	}

	/**
	 * Test bitwise OR: 1010 | 1100 = 1110
	 */
	@Test
	public void bitwiseOr() throws Exception {
		this.mvc.perform(get("/or").param("operand1","1010").param("operand2","1100"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1110"));
	}

	/**
	 * Test bitwise OR with JSON response: 1001 | 0110 = 1111
	 */
	@Test
	public void bitwiseOrJson() throws Exception {
		this.mvc.perform(get("/or_json").param("operand1","1001").param("operand2","110"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(110))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
	}

	// ========== Additional Comprehensive Test Cases ==========

	/**
	 * Test addition with edge case: 0 + 111 = 111
	 */
	@Test
	public void addWithZero() throws Exception {
		this.mvc.perform(get("/add").param("operand1","0").param("operand2","111"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("111"));
	}

	/**
	 * Test multiplication with zero: 111 * 0 = 0
	 */
	@Test
	public void multiplyWithZero() throws Exception {
		this.mvc.perform(get("/multiply").param("operand1","111").param("operand2","0"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("0"));
	}

	/**
	 * Test multiplication with one: 1010 * 1 = 1010
	 */
	@Test
	public void multiplyWithOne() throws Exception {
		this.mvc.perform(get("/multiply").param("operand1","1010").param("operand2","1"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1010"));
	}

	/**
	 * Test AND with zero: 1111 & 0 = 0
	 */
	@Test
	public void andWithZero() throws Exception {
		this.mvc.perform(get("/and").param("operand1","1111").param("operand2","0"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("0"));
	}

	/**
	 * Test AND with itself: 1010 & 1010 = 1010
	 */
	@Test
	public void andWithItself() throws Exception {
		this.mvc.perform(get("/and").param("operand1","1010").param("operand2","1010"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1010"));
	}

	/**
	 * Test OR with zero: 1010 | 0 = 1010
	 */
	@Test
	public void orWithZero() throws Exception {
		this.mvc.perform(get("/or").param("operand1","1010").param("operand2","0"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1010"));
	}

	/**
	 * Test OR with all ones: 1010 | 1111 = 1111
	 */
	@Test
	public void orWithAllOnes() throws Exception {
		this.mvc.perform(get("/or").param("operand1","1010").param("operand2","1111"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1111"));
	}

	/**
	 * Test AND with different lengths: 11 & 10101 = 1
	 */
	@Test
	public void andWithDifferentLengths() throws Exception {
		this.mvc.perform(get("/and").param("operand1","11").param("operand2","10101"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
	}

	/**
	 * Test OR with different lengths: 101 | 11000 = 11101
	 */
	@Test
	public void orWithDifferentLengths() throws Exception {
		this.mvc.perform(get("/or").param("operand1","101").param("operand2","11000"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("11101"));
	}

	/**
	 * Test multiplication with different lengths: 11 (3) * 1000 (8) = 11000 (24)
	 */
	@Test
	public void multiplyWithDifferentLengths() throws Exception {
		this.mvc.perform(get("/multiply").param("operand1","11").param("operand2","1000"))//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string("11000"));
	}

}