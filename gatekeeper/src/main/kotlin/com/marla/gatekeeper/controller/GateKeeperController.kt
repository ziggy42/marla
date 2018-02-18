package com.marla.gatekeeper.controller

import com.marla.gatekeeper.model.Enabled
import com.marla.gatekeeper.service.GateKeeperService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GateKeeperController(private val gateKeeperService: GateKeeperService) {

    @GetMapping
    fun isEnabled(): Enabled = Enabled(gateKeeperService.isEnabled())
}