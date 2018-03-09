package com.marla.worker.service

import com.marla.worker.config.SSHConfiguration
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.stream.Collectors

fun InputStream.collect(): String? =
    BufferedReader(InputStreamReader(this)).lines().collect(Collectors.joining("\n"))

@Service
class SSHShellService(private val config: SSHConfiguration) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun executeCommand(command: String): String? {
        val process = Runtime.getRuntime().exec(buildCommand(command))
        val error = process.errorStream.collect()
        if (error?.isNotEmpty() == true) {
            log.error("Error executing shell command: $error")
        }

        return process.inputStream.collect()
    }

    private fun buildCommand(command: String): String =
        "sshpass -p ${config.password} ssh -o StrictHostKeyChecking=no ${config.username}@${config.host}" +
            " <<-\\SSH\n$command\nSSH"
}