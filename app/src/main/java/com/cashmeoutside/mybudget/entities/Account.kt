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

//    @Id var id: Long = 0
//    var name = ""
//    var accountNumber = ""
//    var balance = 0.toDouble()
//    var accountType = ""
//
//    constructor(name: String, accountNumber: String, balance: Double, accountType: String) : this {
//        this.name = name
//        this.accountNumber = accountNumber
//        this.balance = balance
//        this.accountType = accountType
//    }

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