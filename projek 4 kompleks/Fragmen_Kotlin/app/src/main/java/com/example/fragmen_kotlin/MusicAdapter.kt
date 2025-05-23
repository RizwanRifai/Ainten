package com.example.fragmen_kotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmen_kotlin.databinding.ItemMusicBinding
import com.example.fragmen_kotlin.model.Music

class MusicAdapter(private val context: Context, private val list: List<Music>) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPlaying: Int = -1

    inner class MusicViewHolder(val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val music = list[position]
        holder.binding.tvJudul.text = music.judul
        holder.binding.tvPenyanyi.text = music.penyanyi

        holder.binding.btnPlay.setOnClickListener {
            val url = music.url
            if (!url.isNullOrEmpty()) {
                if (currentPlaying == position && mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                    holder.binding.btnPlay.setImageResource(android.R.drawable.ic_media_play) // ganti ke ikon play
                } else {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(url)
                        prepare()
                        start()
                    }
                    currentPlaying = position
                    holder.binding.btnPlay.setImageResource(android.R.drawable.ic_media_pause) // ganti ke ikon pause
                }
            } else {
                Toast.makeText(context, "Link lagu tidak tersedia", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount() = list.size
}
