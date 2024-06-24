package com.babak.lazaapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babak.lazaapp.databinding.ItemBrandBinding
import com.babak.lazaapp.model.BrandModel

class BrandAdapter:RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private val brandList = arrayListOf<BrandModel>()

    inner class BrandViewHolder( val itemBrandBinding: ItemBrandBinding):RecyclerView.ViewHolder(itemBrandBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = ItemBrandBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BrandViewHolder(view)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = brandList[position]
        holder.itemBrandBinding.brand=brand
    }

    fun updateList(newList:List<BrandModel>){
        brandList.clear()
        brandList.addAll(newList)
        notifyDataSetChanged()
    }
}