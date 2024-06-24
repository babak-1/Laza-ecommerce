package com.babak.lazaapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babak.lazaapp.databinding.ItemProductBinding
import com.babak.lazaapp.model.ProductResponse

class ProductAdapter:RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    private val productList = arrayListOf<ProductResponse>()
    inner class ProductViewHolder(val itemProductBinding: ItemProductBinding):RecyclerView.ViewHolder(itemProductBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
       return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.itemProductBinding.product = product
    }

    fun updateList(newList:List<ProductResponse>){
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}