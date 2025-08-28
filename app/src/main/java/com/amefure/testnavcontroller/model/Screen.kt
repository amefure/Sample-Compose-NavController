package com.amefure.testnavcontroller.model

sealed class Screen {
    abstract fun route(): String

    data object Home : Screen() {
        override fun route() = "home"
    }

    data object Settings : Screen() {
        override fun route() = "settings"
    }

    data object Detail : Screen() {
        const val ARG_ITEM_ID = "itemId"
        /** pattern */
        override fun route() = "detail/{$ARG_ITEM_ID}"
        fun route(itemId: Int) = "detail/$itemId"
    }
}


