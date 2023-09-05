package com.example.carapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCars: RecyclerView
    private val list = ArrayList<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCars = findViewById(R.id.rv_cars)
        rvCars.setHasFixedSize(true)

        list.addAll(getListCars())
        showRecyclerList()
    }

    //get list car
    private fun getListCars(): ArrayList<Car> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCar = ArrayList<Car>()
        for (i in dataName.indices) {
            val hero = Car(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listCar.add(hero)
        }
        return listCar
    }

    private fun showRecyclerList() {
        rvCars.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListCarAdapter(list)
        rvCars.adapter = listHeroAdapter
    }

    //add menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvCars.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvCars.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
