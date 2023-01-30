package fr.isen.Nicolas.AndroidERestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ViewPagerAdapter(val images : java.util.ArrayList<String>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image : ImageView = view.findViewById(R.id.imageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.caroussel, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        if(image != "") Picasso.get().load(image).into(holder.image)

    }

    override fun getItemCount(): Int = images.size
}