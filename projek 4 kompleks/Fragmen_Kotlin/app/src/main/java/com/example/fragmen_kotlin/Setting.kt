
package com.example.fragmen_kotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Collections.list
import java.util.Date
import java.util.Locale


class Setting : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_setting, container, false)
        val textView = rootView.findViewById<TextView>(R.id.history_text)

        val prefs = requireContext().getSharedPreferences("komik_history", Context.MODE_PRIVATE)
        val historySet = prefs.getStringSet("history_set", emptySet())

        val historyText = historySet?.joinToString(separator = "\n") ?: "Belum ada riwayat."
        textView.text = "Riwayat Komik:\n$historyText"

        return rootView
    }
}
