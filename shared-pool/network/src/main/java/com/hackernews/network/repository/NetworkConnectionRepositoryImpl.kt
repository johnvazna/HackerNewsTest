package com.hackernews.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hackernews.network.repository.data.NetworkConnectionApiService
import org.koin.java.KoinJavaComponent.inject

class NetworkConnectionRepositoryImpl : NetworkConnectionRepository {

    /* */
    private val networkConnectionApiService by inject(NetworkConnectionApiService::class.java)

    /* */
    private var _isOnline: Boolean = false
    override val isOnline: Boolean get() = _isOnline

    /* */
    private val _isOnlineLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val isOnlineLiveData: LiveData<Boolean>
        get() = _isOnlineLiveData

    /** */
    override suspend fun fetch(){
        _isOnline = try {
            networkConnectionApiService.generate204()
            true
        } catch (exception: Exception) { false }
        _isOnlineLiveData.postValue(_isOnline)
    }

}