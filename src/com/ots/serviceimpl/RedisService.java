package com.ots.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class RedisService {
	
	@Autowired
	private JedisPool jedisPool;
	//封装redis-set方法

	public String set(String key,String value){
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			return jedis.set(key, value);
		} finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	
	
	public String get(String key){
		Jedis jedis = null;
		try {
			jedis = this.jedisPool.getResource();
			return jedis.get(key);
		} finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}

}
