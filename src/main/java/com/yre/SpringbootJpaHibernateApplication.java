package com.yre;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.yre.mapper")
public class SpringbootJpaHibernateApplication {

	/**
	 * 这里和以往不一样的地方就是MapperScan的注解，这个是会扫描该包下的接口
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled","false");
		SpringApplication.run(SpringbootJpaHibernateApplication.class, args);
	}

	/*
	* fastjson
	* 是一个Java语言编写的高性能功能完善的JSON库。
	* 它的速度之快超过了所有json库。
	* 甚至是以前最快的jackson.
	* */


	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {

		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		/**
		 * 解决乱码
		 */
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}




}
