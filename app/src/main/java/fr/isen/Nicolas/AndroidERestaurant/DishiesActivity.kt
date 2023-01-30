package fr.isen.Nicolas.AndroidERestaurant


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.Nicolas.AndroidERestaurant.databinding.ActivityDishiesBinding
import org.json.JSONObject

class DishiesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDishiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryList.layoutManager = LinearLayoutManager(this)

        val title = intent.getStringExtra("Category")
        binding.category.text = title

        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = CategoryAdapter(arrayListOf()) {
            val intent = Intent(this, DishActivity::class.java)
            startActivity(intent)
        }

        loadDishesFromAPI(title)

        binding.button1.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadDishesFromAPI(category: String?) {

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, {
                val gson = GsonBuilder().create()
                val data = gson.fromJson(it.toString(), Menu::class.java)
                for(data in data.data) {
                    val dishes = data.items
                    if(category == data.name_fr) {
                        for (items in data.items) {
                            binding.categoryList.layoutManager = LinearLayoutManager(this)
                            binding.categoryList.adapter = CategoryAdapter(dishes) {
                                val intent = Intent(this, DishActivity::class.java)
                                intent.putExtra("Category", it.categ_name_fr)
                                intent.putExtra("Dish Name", it.name_fr)
                                intent.putExtra("Dish Price", it.prices[0].price)
                                intent.putExtra("Dish Img", it.images)
                                val ingredientsList : ArrayList<String> =  ArrayList()
                                for (ingredients in it.ingredients) {
                                    ingredientsList.add(ingredients.name_fr)
                                    intent.putExtra("Dish Ingredient", ingredientsList)
                                }
                                startActivity(intent)
                            }
                        }
                    }
                }
            }, {
                Log.w("CategoryActivity", "erreur : $it")
            }
        )
        Volley.newRequestQueue(this).add(jsonRequest)
    }


    data class Menu (
        var data: ArrayList<Data>
    )

    data class Data (
        var name_fr: String,
        var name_en: String,
        var items: ArrayList<Item>
    )

    data class Item (
        var id: Int,
        var name_fr: String,
        var name_en: String,
        var id_category: Int,
        var categ_name_fr: String,
        var categ_name_en: String,
        var images: ArrayList<String>,
        var ingredients: ArrayList<Ingredient>,
        var prices: ArrayList<Price>
    )

    data class Ingredient (
        var id: Int,
        var id_shop: Int,
        var name_fr: String,
        var name_en: String,
        var create_date: String,
        var update_date: String,
        var id_pizza: Int
    )

    data class Price (
        var id: Int,
        var id_pizza: Int,
        var id_size: Int,
        var price: Float,
        var create_date: String,
        var update_date: String,
        var size: String
    )
}
