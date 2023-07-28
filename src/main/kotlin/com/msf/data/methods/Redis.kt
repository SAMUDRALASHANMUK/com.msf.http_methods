package com.msf.data.methods

import com.msf.data.model.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import redis.clients.jedis.Jedis


const val redisHost = "localhost"
const val redisPort = 6379

//  Function to create a Redis connection
suspend fun createRedisConnection(): Jedis = withContext(Dispatchers.IO) {
    Jedis(redisHost, redisPort)
}

//  Function to save the user session to Redis
suspend fun saveSessionToRedis(userSession: UserSession) {
    val redis = createRedisConnection()
    redis.setex("user_session:${userSession.sessionId}", 300, userSession.toString())
    redis.close()
}

//  function to remove the user session from Redis
suspend fun removeSessionFromRedis(sessionId: String) {
    val redis = createRedisConnection()
    redis.del("user_session:$sessionId")
    redis.close()
}