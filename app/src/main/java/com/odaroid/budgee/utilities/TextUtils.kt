package com.odaroid.budgee.utilities

import android.text.Editable
import android.text.TextWatcher

interface TextWatcherImpl : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}