package com.marla.worker

fun main(args: Array<String>) {
    generateSequence { getJob() }.forEach(::processNewJob)
}