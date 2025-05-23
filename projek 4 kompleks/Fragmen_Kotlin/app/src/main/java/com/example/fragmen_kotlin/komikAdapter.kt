
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragmen_kotlin.R
import com.example.fragmen_kotlin.chapter
import com.example.fragmen_kotlin.model.Komik

class KomikAdapter(
    private val listKomik: List<Komik>,
    private val context: Context
) : RecyclerView.Adapter<KomikAdapter.KomikViewHolder>() {

    class KomikViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title_komik)
        val genre = view.findViewById<TextView>(R.id.genre_komik)
        val sinopsis = view.findViewById<TextView>(R.id.sinopsis_komik)
        val image = view.findViewById<ImageView>(R.id.image_komik)
        val btnChapter1 = view.findViewById<Button>(R.id.btn_1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KomikViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_komik, parent, false)
        return KomikViewHolder(view)
    }

    override fun onBindViewHolder(holder: KomikViewHolder, position: Int) {
        val komik = listKomik[position]

        holder.title.text = komik.title
        holder.genre.text = "genre: ${komik.genre}"
        holder.sinopsis.text = komik.synopsis

        Glide.with(context)
            .load(komik.imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .into(holder.image)

        holder.btnChapter1.setOnClickListener {
            val intent = Intent(context, chapter::class.java)
            intent.putExtra("namaKomik", komik.baca)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listKomik.size
}


