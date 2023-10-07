package com.example.traveler.util

import android.text.Editable
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("android:textInt")
fun setTextInt(view: EditText, value: Int?) {
    view.setText(value?.toString() ?: "")
}