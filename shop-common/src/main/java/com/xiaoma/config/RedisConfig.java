package com.xiaoma.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * 底层redis自动配置中的
 * @ConditionalOnMissingBean(name = "redisTemplate")
 * 会检测Spring容器中是否有一个id为redisTemplate的Bean,如果有,就用现成的,不在使用默认配置去初始化
 * 如果没有,用默认配置初始化
 *
 * @SuppressWarnings("ALL")抑制所有警告
 */
@SuppressWarnings("ALL")
@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		//设置序列化器
		StringRedisSerializer serializer = new StringRedisSerializer();
		template.setKeySerializer(serializer);
		template.setValueSerializer(serializer);

		template.setHashKeySerializer(serializer);
		template.setHashValueSerializer(serializer);
		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.disableCachingNullValues()
				.serializeKeysWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()));
		return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();
	}
}
