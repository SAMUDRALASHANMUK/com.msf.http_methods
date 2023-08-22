package com.msf.util.redis

import com.msf.data.model.UserSession
import com.msf.util.appconstants.GlobalConstants.REDIS_EXPIRATION_SECONDS
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.HoconApplicationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import redis.clients.jedis.Jedis

val config = HoconApplicationConfig(ConfigFactory.load())
val redisHost = config.property("host").getString()
val redisPort = Integer.parseInt(config.property("port").toString())

//  Function to create a Redis connection
suspend fun createRedisConnection(): Jedis = withContext(Dispatchers.IO) {
    Jedis(redisHost, redisPort)
}

//  Function to save the user session to Redis
suspend fun saveSessionToRedis(userSession: UserSession) {
    val redis = createRedisConnection()
    redis.setex("user_session:${userSession.sessionId}", REDIS_EXPIRATION_SECONDS, userSession.toString())
    redis.close()
}

//  function to remove the user session from Redis
suspend fun removeSessionFromRedis(sessionId: String) {
    val redis = createRedisConnection()
    redis.del("user_session:$sessionId")
    redis.close()
}
