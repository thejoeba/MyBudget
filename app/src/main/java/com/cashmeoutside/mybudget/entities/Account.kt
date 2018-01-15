package com.cashmeoutside.mybudget.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Account(
        @Id var id: Long = 0,
        var name: String,
        var accountNumber: String,
        var balance: Double,
        var accountType: String
) {
    enum class AccountTypeEnum constructor(private val mName: String) {
        CHECKING("Checking"),
        SAVINGS("Savings"),
        INVESTMENT("Investment"),
        CREDIT_CARD("Credit Card");

        override fun toString(): String {
            return mName
        }
    }
}