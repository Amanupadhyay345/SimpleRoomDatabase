package com.example.simpleroomdatabase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleroomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var binding: ActivityMainBinding
    private lateinit var expenseAdapter: ExpenseAdapter
    private var expenseList = mutableListOf<Expance>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Initialize the database
        db = DatabaseHelper.getDatabase(applicationContext)

        // Set up RecyclerView
        binding.expenseRecyclerView.layoutManager = LinearLayoutManager(this)
        expenseAdapter = ExpenseAdapter(expenseList,object : delete{
            override fun onDelete(expance: Expance) {

              CoroutineScope(Dispatchers.IO).launch {
                  db.userDao().deletedata(expance)
                  loadExpenses()
              }

            }

        })
        binding.expenseRecyclerView.adapter = expenseAdapter

        // Load existing expenses
        loadExpenses()




        // Handle button click for adding expense
        binding.addButton.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val amount = binding.editTextAmount.text.toString()

            if (title.isNotEmpty() && amount.isNotEmpty()) {
                val expense = Expance(0, title, amount)
                addExpense(expense)
            } else {
                Toast.makeText(this, "Please enter both title and amount", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addExpense(expense: Expance) {
        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().adddata(expense)
            loadExpenses() // Refresh the list after adding a new expense
        }
    }

    private fun loadExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            val expenses = db.userDao().getAllExpense()
            runOnUiThread {
                expenseList.clear()
                expenseList.addAll(expenses)
                expenseAdapter.notifyDataSetChanged()
            }
        }
    }
}
