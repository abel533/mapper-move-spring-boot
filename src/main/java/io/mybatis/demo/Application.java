package io.mybatis.demo;

import io.mybatis.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Spring Boot 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "io.mybatis.demo.mapper")
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        logger.info("----------当前顺序----------");
        userService.getAll();
        logger.info("将 3 号挪到 7 号后面");
        userService.move(3L, 7L, false);
        logger.info("----------移动后的顺序----------");
        userService.getAll();
        logger.info("将 4 号挪到 8 号前面");
        userService.move(4L, 8L, true);
        logger.info("----------移动后的顺序----------");
        userService.getAll();
        logger.info("将 9 号挪到 2 号前面");
        userService.move(9L, 2L, true);
        logger.info("----------移动后的顺序----------");
        userService.getAll();
        logger.info("将 10 号挪到 1 号后面");
        userService.move(10L, 1L, false);
        logger.info("----------移动后的顺序----------");
        userService.getAll();
    }

}
