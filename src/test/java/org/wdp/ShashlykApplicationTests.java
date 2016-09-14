package org.wdp;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.wdp.shashlyk.classifier.DishClass;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ShashlykApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void canSeeCategories() throws Exception {
		this.mvc.perform(
			MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML)
		).andExpect(
			MockMvcResultMatchers.content().string(
				CoreMatchers.containsString(
					DishClass.PLEBS.getTitle()
				)
			)
		);
	}
}
