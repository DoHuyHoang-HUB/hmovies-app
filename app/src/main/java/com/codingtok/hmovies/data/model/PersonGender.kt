package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.enums.ValueEnum

enum class PersonGender(override val value: Int): ValueEnum {
    OTHER(0),
    FEMALE(1),
    MALE(2)
}
