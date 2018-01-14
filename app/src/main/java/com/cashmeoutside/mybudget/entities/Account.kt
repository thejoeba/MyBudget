package com.cashmeoutside.mybudget.entities

class Account(val Name: String, val AccountNumber: String, val Balace: Double, val AccountType: String) {

    enum class AccountTypeEnum private constructor(private val mName: String) {
        CHECKING("Checking"),
        SAVINGS("Savings"),
        INVESTMENT("Investment"),
        CREDIT_CARD("Credit Card");

        override fun toString(): String {
            return mName
        }
    }
}