package fr.isen.Nicolas.AndroidERestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import fr.isen.Nicolas.AndroidERestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intent = Intent(this, DishiesActivity::class.java)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button9.setOnClickListener{
            Snackbar.make(it,"Entrées", Snackbar.LENGTH_SHORT).show()
            Intent(this, DishiesActivity::class.java)
            intent.putExtra("Category", "Entrées")
            startActivity(intent)
            finish()
        }
        binding.button10.setOnClickListener {
            Snackbar.make(it, "Plats", Snackbar.LENGTH_SHORT).show()
            Intent(this, DishiesActivity::class.java)
            intent.putExtra("Category", "Plats")
            startActivity(intent)
            finish()
        }
        binding.button11.setOnClickListener{
            Snackbar.make(it,"Desserts", Snackbar.LENGTH_SHORT).show()
            Intent(this, DishiesActivity::class.java)
            intent.putExtra("Category", "Desserts")
            startActivity(intent)
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "Activity destroyed")
    }
}