package com.marla.worker

import monkey.`object`.Environment
import monkey.ast.Parser
import monkey.evaluator.Evaluator
import monkey.lexer.StringLexer

fun main(args: Array<String>) {
    val program = Parser(StringLexer("3 + 3")).parseProgram()
    println(Evaluator.eval(program, Environment()).inspect())
}