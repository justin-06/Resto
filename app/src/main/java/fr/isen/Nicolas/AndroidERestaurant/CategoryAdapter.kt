package fr.isen.Nicolas.AndroidERestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoryAdapter(var dishies: List<DishiesActivity.Item> , val onItemLongClickListener:(DishiesActivity.Item)->Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val cellName : TextView = view.findViewById<TextView>(R.id.title_dishies)
        val cellPrice : TextView = view.findViewById<TextView>(R.id.price_dishies)
        val image : ImageView = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val dish = dishies[position]
        holder.cellName.text = dish.name_fr
        val dish_price : String = "" + dish.prices[0].price + " €"
        holder.cellPrice.text = dish_price
        if(dish.images[0] != "")Picasso.get().load(dish.images[0]).into(holder.image)

        holder.itemView.setOnClickListener(){
            onItemLongClickListener(dish)
        }
    }
    override fun getItemCount(): Int = dishies.size

}
