package my.study.cryptolist.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import my.study.cryptolist.R
import my.study.cryptolist.databinding.ItemCoinInfoBinding
import my.study.cryptolist.pojo.CoinPriceInfo

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    inner class CoinInfoViewHolder(private val itemView: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bind(coin: CoinPriceInfo) {
            with(itemView){
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                val tvSymbols = findViewById<TextView>(R.id.tvSymbols)
                val tvPrice = findViewById<TextView>(R.id.tvPrice)
                val tvLastUpdate = findViewById<TextView>(R.id.tvLastUpdate)
                val coinImage = findViewById<ImageView>(R.id.coinImage)
                tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
                tvPrice.text = coin.price.toString().take(8) //TODO сделать ограничение именно после запятой
                tvLastUpdate.text = String.format(lastUpdateTemplate, coin.getFormattedTime())
                Picasso.get().load(coin.getFullImageUrl()).into(coinImage)
            }
            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCoinInfo = ItemCoinInfoBinding.inflate(layoutInflater, parent, false)
        return CoinInfoViewHolder(itemCoinInfo)
    }

    override fun getItemCount(): Int = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.bind(coin)
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}