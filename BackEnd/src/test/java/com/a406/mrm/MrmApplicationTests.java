package com.a406.mrm;

import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.Room;
import com.a406.mrm.repository.CategoryRepository;
import com.a406.mrm.service.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MrmApplicationTests {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@Test
	public void createtest() {
		Room room = new Room();
		room.setId(3);
		room.setName("new test");
		Category category = new Category();
		category.setName("new category");
		category.setRoom(room);

		categoryRepository.save(category);
	}

	@Test
	public void updatetest() {
		int id = 3;
		String name = "update test";
		categoryServiceImpl.update(id,name);
	}

//	@Test
//	public void deletetest() {
//		int id = 6;
//		categoryRepository.deleteById(id);
//	}

}
