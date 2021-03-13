package com.warthur.nacos.demo.config.redis;

import org.springframework.data.redis.connection.RedisConnection;

/**
 * 批量执行redis命令接口
 * @author warthur
 */
public interface CachePipeline {

	/**
	 * 执行redis命令
	 * @param connection redis connection
	 */
	void executeCommand(RedisConnection connection);
}
