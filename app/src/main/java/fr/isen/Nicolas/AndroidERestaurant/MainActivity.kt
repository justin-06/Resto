package fr.isen.Nicolas.AndroidERestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionEntree = findViewById<Button>(R.id.button9)
        actionEntree.setOnClickListener{
            Snackbar.make(it,"Entrées", Snackbar.LENGTH_SHORT).show()
        }
        val actionPlat = findViewById<Button>(R.id.button10)
        actionPlat.setOnClickListener{
            Snackbar.make(it,"Entrées", Snackbar.LENGTH_SHORT).show()
        }
        val actionDessert = findViewById<Button>(R.id.button11)
        actionDessert.setOnClickListener{
            Snackbar.make(it,"Entrées", Snackbar.LENGTH_SHORT).show()
        }
    }
}