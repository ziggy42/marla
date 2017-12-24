package com.marla.api.service.job

import com.marla.api.model.Job
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
internal class RedisJobsQueueTest {

    @Autowired
    private lateinit var jobsQueue: RedisJobsQueue

    @Test
    fun testPublish() {
        val result = jobsQueue.publish(Job("id", "content"))
        assertTrue(result)
    }
}