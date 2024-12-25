package com.example.cryptoapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.databinding.ViewholderCryptoBinding
import com.example.cryptoapp.databinding.ViewholderStockBinding
import com.example.cryptoapp.model.Model
import java.text.DecimalFormat

class StockAdapter(private val dataList:ArrayList<Model>):
RecyclerView.Adapter<StockAdapter.ViewHolder>(){
    private val formatter=DecimalFormat("###,###,###,###.##")
    class ViewHolder(val binding: ViewholderStockBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAdapter.ViewHolder {
        val binding=ViewholderStockBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
        val item=dataList[position]
        holder.binding.apply {
            cryptoNameTxt.text=item.name
            cryptoPriceTxt.text="$${formatter.format(item.price)}"
            changePercentTxt.text="${item.changePercent}%"
            sparkLineLayout.setData(item.lineData)
            val changeColor=when{
                item.changePercent>0->Color.parseColor("#12C737")
                item.changePercent<0->Color.parseColor("#FF0000")
                else->Color.WHITE
            }
            val picName=when(item.name){
                "NASDAQ100"->"stock_1"
                "S&P 500"->"stock_2"
                "Dow Jones"->"stock_3"
                else->""
            }
            changePercentTxt.setTextColor(changeColor)
            sparkLineLayout.sparkLineColor=changeColor
            val drawableResourceId=holder.itemView.context.resources.getIdentifier(picName,"drawable",holder.itemView.context.packageName)
            Glide.with(holder.itemView.context).load(drawableResourceId).into(logoImg)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}