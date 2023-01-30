package fr.isen.Nicolas.AndroidERestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.Nicolas.AndroidERestaurant.databinding.ActivityDishBinding
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding
    private lateinit var viewPager: ViewPager2
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.priceButton.text = "Total : 0.0 €"

        val category = intent.getStringExtra("Category")
        val dishName = intent.getStringExtra("Dish Name")
        binding.titleDish.text = dishName
        val dishPrice = intent.getFloatExtra("Dish Price", 0.0F)
        binding.priceDish.text = "Prix Unitaire : " + dishPrice + " €"
        val dishIngredient = intent.getStringArrayListExtra("Dish Ingredient")
        binding.ingredientDish.text = dishIngredient.toString()

        viewPager = findViewById<ViewPager2>(R.id.dish_viewPager)
        val dishImg = intent.getStringArrayListExtra("Dish Img") as ArrayList
        binding.dishViewPager.adapter = ViewPagerAdapter(dishImg)

        binding.button1.setOnClickListener{
            Snackbar.make(it,"Retour", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this, DishiesActivity::class.java)
            intent.putExtra("Category",category)
            startActivity(intent)
        }
        var quantity = 0
        val gson = Gson()
        val totalPrice = TotalPrice(tot_price = quantity * dishPrice)
        /*
        val json = gson.toJson(totalPrice)
        val file = File(context.filesDir, "total_price.json")
        val fileWriter = FileWriter(file)
        fileWriter.close()
        */
        binding.quantityDish.text = "$quantity"
        binding.plusButton.setOnClickListener{
            quantity ++
            binding.quantityDish.text = "$quantity"
            /*
            val fileReader = FileReader(file)
            val totalPrice = gson.fromJson(fileReader, TotalPrice::class.java)
            fileReader.close()
            totalPrice.tot_price = totalPrice.tot_price + dishPrice
            binding.priceButton.text = "Total : " + totalPrice.tot_price + " €"
            fileWriter.write(json)
            fileWriter.flush()
            fileWriter.close()
            */
            totalPrice.tot_price = totalPrice.tot_price + dishPrice
            binding.priceButton.text = "Total : " + totalPrice.tot_price + " €"
        }
        binding.minusButton.setOnClickListener{
            if(quantity > 0){
                quantity --
                binding.quantityDish.text = "$quantity"
                /*
                val fileReader = FileReader(file)
                val totalPrice = gson.fromJson(fileReader, TotalPrice::class.java)
                fileReader.close()
                totalPrice.tot_price = totalPrice.tot_price - dishPrice
                binding.priceButton.text = "Total : " + totalPrice.tot_price + " €"
                fileWriter.write(json)
                fileWriter.flush()
                fileWriter.close()
                */
                totalPrice.tot_price = totalPrice.tot_price - dishPrice
                binding.priceButton.text = "Total : " + totalPrice.tot_price + " €"
            }
        }
    }
    data class TotalPrice (
        var tot_price : Float = 0.0F
    )
}