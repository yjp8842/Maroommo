package com.a406.mrm;


import com.a406.mrm.controller.RoomController;
import com.a406.mrm.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MrmApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(MrmApplicationTests.class);
	@Autowired
	RoomRepository roomRepository;

	@Test
	public void test() {
	}


}
