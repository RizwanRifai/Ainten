package com.example.fragmen_kotlin

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmen_kotlin.adapter.MusicAdapter
import com.example.fragmen_kotlin.databinding.FragmentProfileBinding
import com.example.fragmen_kotlin.model.Music
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class Profile : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMusicData()
    }

    private fun fetchMusicData() {
        val request = Request.Builder()
            .url("https://ynrxgkbvrpnnzczeaakl.supabase.co/rest/v1/music?select=*")
            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlucnhna2J2cnBubnpjemVhYWtsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDczMTI4NDUsImV4cCI6MjA2Mjg4ODg0NX0.q_tIHhWkJrcj5YYTMvu4DvTxCBcD1J5ShYnlPE5fAl8")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { json ->
                    val type = object : TypeToken<List<Music>>() {}.type
                    val musicList: List<Music> = Gson().fromJson(json, type)
                    activity?.runOnUiThread {
                        binding.recyclerView.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MusicAdapter(requireContext(), musicList)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}