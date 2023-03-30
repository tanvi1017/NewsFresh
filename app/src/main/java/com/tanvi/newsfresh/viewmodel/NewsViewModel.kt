package com.tanvi.newsfresh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tanvi.newsfresh.MyApplication
import com.tanvi.newsfresh.NewsResponse
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.service.AppRepository
import com.tanvi.newsfresh.util.Resources
import com.tanvi.newsfresh.util.Utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(app: Application, private val appRepository: AppRepository):AndroidViewModel(app) {
    val newsData: MutableLiveData<Resources<NewsResponse>> = MutableLiveData()
    private fun getNews(q: String, apiKey: String, pageCount: Int, pageNumber: Int) = viewModelScope.launch{
        fetchNews(q, apiKey, pageCount, pageNumber)
    }
     suspend fun fetchNews(q: String, apiKey: String, pageCount: Int, pageNumber: Int){
        newsData.postValue(Resources.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getNews(q,apiKey,pageCount,pageNumber)
                newsData.postValue(handlePicsResponse(response))
            } else {
                newsData.postValue(Resources.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
            }
        }catch (t: Throwable) {
            when (t) {
                is IOException -> newsData.postValue(
                    Resources.Error(
                        getApplication<MyApplication>().getString(
                            R.string.network_failure
                        )))
                else -> newsData.postValue(
                    Resources.Error(getApplication<MyApplication>().getString(R.string.conversion_error)))
            }
        }
    }
    private fun handlePicsResponse(response: Response<NewsResponse>): Resources<NewsResponse>? {

        if (response.isSuccessful){
            response.body()?.let{
                return Resources.Success(it) }
        }
        return Resources.Error(response.message())
    }


}