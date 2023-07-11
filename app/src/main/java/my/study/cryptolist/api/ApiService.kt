package my.study.cryptolist.api

import io.reactivex.rxjava3.core.Single
import my.study.cryptolist.pojo.CoinInfoListOfData
import my.study.cryptolist.pojo.CoinPriceInfoRawData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = "5c349c9c4b9237cca0d640f01b5f20a54edbf0837eb1b3a89119f175f2f6f9ee",
        @Query(QUERY_PARAM_LIMIT) limit : Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym : String = CURRENCY,
    ) : Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = "5c349c9c4b9237cca0d640f01b5f20a54edbf0837eb1b3a89119f175f2f6f9ee",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms : String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms : String = CURRENCY,
    ): Single<CoinPriceInfoRawData>

    companion object{
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}