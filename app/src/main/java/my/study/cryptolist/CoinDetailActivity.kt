package my.study.cryptolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import my.study.cryptolist.databinding.ActivityCoinDetailBinding
import my.study.cryptolist.databinding.ActivityCoinPriceListBinding
import my.study.cryptolist.models.CoinViewModel

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinDetailBinding
    private lateinit var viewModel: CoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }

        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer{
                binding.tvPrice.text = it.price.toString()
                binding.minimalPriceDay.text = it.lowDay.toString()
                binding.maximumPriceDay.text = it.highDay.toString()
                binding.lastDeal.text = it.lastMarket.toString()
                binding.lastUpdate.text = it.getFormattedTime()
                binding.tvFromSymbol.text = it.fromSymbol
                binding.tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.getFullImageUrl()).into(binding.ivLogoCoin)
            })
        }
    }
    companion object{
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent
        {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}