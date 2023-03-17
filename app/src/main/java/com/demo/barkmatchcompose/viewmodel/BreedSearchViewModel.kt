package com.demo.barkmatchcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.barkmatchcompose.application.BarkMatchComposeApplication
import com.demo.barkmatchcompose.model.MatchResult
import com.demo.barkmatchcompose.util.SingleEventLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedSearchViewModel: ViewModel() {

     val matchResults: SingleEventLiveData<MatchResult> = SingleEventLiveData()
     val errorMessage: SingleEventLiveData<String> = SingleEventLiveData()

    fun searchDogsByBreed(breed: String): LiveData<MatchResult> {

        BarkMatchComposeApplication.getInstance().getAPI()?.getImagesByBreed(breed)?.enqueue(object:
            Callback<MatchResult> {
            override fun onResponse(call: Call<MatchResult>, response: Response<MatchResult>) {
                matchResults.postValue(response.body())
            }

            override fun onFailure(call: Call<MatchResult>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })

        return matchResults
    }


    }
