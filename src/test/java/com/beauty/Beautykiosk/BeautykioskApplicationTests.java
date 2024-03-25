package com.beauty.Beautykiosk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.beauty.Beautykiosk.Goods.GoodsService;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private GoodsService goodsService;

	@Test
	void testJpa() {
		for (int i = 1; i <= 300; i++) {
			String name = String.format("테스트 데이터입니다:[%03d]", i);
			String effect = "내용무";
			String image = "url무";
			Integer number = 0;

			this.goodsService.create(name, effect, image, number);
		}
	}
}