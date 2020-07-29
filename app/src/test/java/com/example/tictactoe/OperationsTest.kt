package com.example.tictactoe

import com.example.tictactoe.utilities.Operations.pow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class OperationsTest : StringSpec({
    "power of an integer" {
        forAll(
            row(1, 1, 1),
            row(2, 2, 4),
            row(2, 3, 8),
            row(10, 2, 100)
        ) { value, power, result ->
            value.pow(power) shouldBe result
        }
    }
})