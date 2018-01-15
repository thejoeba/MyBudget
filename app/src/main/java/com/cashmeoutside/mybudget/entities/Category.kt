package com.cashmeoutside.mybudget.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Category(@Id var id: Long = 0, val Name: String)