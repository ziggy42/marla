package com.marla.worker.service

import com.marla.worker.config.ETHConfiguration
import com.marla.worker.config.SSHConfiguration
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ETHService(
    private val shell: SSHShellService,
    private val config: ETHConfiguration,
    private val sshConfiguration: SSHConfiguration
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun executeTransaction(source: String, destination: String, amount: Double): String? {
        log.debug("Executing transaction from $source to $destination of $amount ETH")

        val result = shell.executeCommand(getGethCommand(source, destination, amount))

        log.debug("Execution result: $result")

        return result
    }

    private fun getGethCommand(source: String, destination: String, amount: Double): String =
        """
        echo -e ${sshConfiguration.password} | sudo -S /usr/local/bin/geth --exec 'personal.unlockAccount("${config.address}", "${config.passphrase}", 300);
        eth.sendTransaction({from:"$source", to:"$destination", value: web3.toWei($amount, "ether")})'
        attach ${config.ipcPath}
        """.trimIndent()
}

// [apivetta@ethereum01 ~]$ sudo /usr/local/bin/geth  --exec 'personal.unlockAccount("0xcd6ae68647cc0818dc9b426de37a7f5ba60c7484", "im0lab", 300); eth.sendTransaction({from:"0xcd6ae68647cc0818dc9b426de37a7f5ba60c7484", to:"0xc2487ed4ecf3ec2fbffb8849c7880ff3167ac698", value: web3.toWei(1, "ether")})' attach /root/ethereum_private_imolab/geth.ipc

// from: 0xcd6ae68647cc0818dc9b426de37a7f5ba60c7484
// to: 0xc2487ed4ecf3ec2fbffb8849c7880ff3167ac698

//             echo -e "pQmiBvy87VkPXynN" | sudo -S geth --exec 'web3.fromWei(eth.getBalance(0xc2487ed4ecf3ec2fbffb8849c7880ff3167ac698), "ether")' attach /root/ethereum_private_imolab/geth.ipc

/*

    private fun getCurrentBalance(account: String): String? = shell.executeCommand(
        "echo -e pQmiBvy87VkPXynN | sudo -S /usr/local/bin/geth --exec 'web3.fromWei(eth.getBalance(\"$account\"), \"ether\")' attach /root/ethereum_private_imolab/geth.ipc"
    )
 */