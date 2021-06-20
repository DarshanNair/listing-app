package com.target.targetcasestudy.payment.manager

import javax.inject.Inject

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */

class Validators @Inject constructor() {
    fun validateCreditCard(creditCardNumber: String): Boolean {
        val cardNumberLength = creditCardNumber.length
        if (cardNumberLength < 13 || cardNumberLength > 19) {
            return false
        }

        //Luhn Algorithm
        val lastDigit = Character.getNumericValue(creditCardNumber[cardNumberLength - 1])
        val sum = creditCardNumber
            .substring(0, cardNumberLength - 1)
            .reversed()
            .map { Character.getNumericValue(it) }
            .mapIndexed { index, value ->
                if (index % 2 == 0) {
                    val newValue = value * 2
                    if (newValue > 9) {
                        newValue - 9
                    } else {
                        newValue
                    }
                } else {
                    value
                }
            }
            .sum()

        return sum % 10 == lastDigit
    }
}