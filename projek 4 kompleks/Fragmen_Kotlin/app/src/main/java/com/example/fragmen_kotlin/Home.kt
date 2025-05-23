package com.example.fragmen_kotlin

import KomikAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.api.ApiClient
import com.example.fragmen_kotlin.model.Komik
import kotlinx.coroutines.launch

class FragmenKomik : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var komikAdapter: KomikAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        val api = ApiClient.create()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val result: List<Komik> = api.getKomikList()
                komikAdapter = KomikAdapter(result, requireContext())
                recyclerView.adapter = komikAdapter
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}