package com.cashmeoutside.mybudget.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index

@Entity
data class AccountType(@Id var id: Long = 0, @Index @JvmField val Name: String)