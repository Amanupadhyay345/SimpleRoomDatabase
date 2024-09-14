package com.example.simpleroomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(private val expenseList: List<Expance>, var delete: delete) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.expenseTitle)
        val amountTextView: TextView = itemView.findViewById(R.id.expenseAmount)
        val  deletebutton : ImageButton = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenseList[position]
        holder.titleTextView.text = expense.title
        holder.amountTextView.text = expense.amount

        holder.deletebutton.setOnClickListener {
            delete.onDelete(expense)

        }


    }

    override fun getItemCount() = expenseList.size
}
