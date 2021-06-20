package com.target.targetcasestudy

import com.target.targetcasestudy.payment.manager.Validators
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
@RunWith(MockitoJUnitRunner::class)
class CreditCardValidatorTest {

    private lateinit var subject: Validators

    @Before
    fun setUp() {
        subject = Validators()
    }

    @Test
    fun `Valid Card input`() {
        // GIVEN
        val validCard = "4556737586899855"

        // WHEN
        val isValid = subject.validateCreditCard(validCard)

        // THEN
        Assert.assertTrue(isValid)
    }

    @Test
    fun `Invalid Card input`() {
        // GIVEN
        val validCard = "455673758689985"

        // WHEN
        val isValid = subject.validateCreditCard(validCard)

        // THEN
        Assert.assertFalse(isValid)
    }

    @Test
    fun `Invalid Card input - digits less than 13`() {
        // GIVEN
        val validCard = "455673758689"

        // WHEN
        val isValid = subject.validateCreditCard(validCard)

        // THEN
        Assert.assertFalse(isValid)
    }

    @Test
    fun `Invalid Card input - digits greater than 19`() {
        // GIVEN
        val validCard = "45567375868912345678"

        // WHEN
        val isValid = subject.validateCreditCard(validCard)

        // THEN
        Assert.assertFalse(isValid)
    }

}
