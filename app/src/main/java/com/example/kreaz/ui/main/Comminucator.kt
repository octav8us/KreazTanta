package com.example.kreaz.ui.main

import com.example.kreaz.network.Item

interface Communicator {
    fun passData(item: Item?, quantity: Int?)
}
