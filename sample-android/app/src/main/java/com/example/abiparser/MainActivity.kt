package com.example.abiparser

import android.app.Activity
import android.os.Bundle
import io.gnosis.safe.examples.EtherToken


class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EtherToken.Name.encode()
    }
}