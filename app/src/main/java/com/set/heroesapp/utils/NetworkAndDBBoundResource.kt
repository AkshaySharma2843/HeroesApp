package com.set.heroesapp.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkAndDBBoundResource<ResultType, RequestType> @MainThread constructor() {

    /**
     * The final result LiveData
     */
    private val result = MediatorLiveData<Resource<ResultType?>>()

    init {
        // Send loading state to UI
        result.value = Resource.loading()

        val dbSource = this.loadFromDb()

        result.addSource(dbSource) { data ->

            result.removeSource(dbSource) // Once done data loading remove source

            if (shouldFetch(data)) {

                fetchFromNetwork(dbSource)

            } else {

                result.addSource(dbSource) { newData -> setValue(Resource.success(newData)) }
            }
        }
    }

    /**
     * Fetch the data from network and persist into DB and then
     * send it back to UI.
     */
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) {
            result.setValue(Resource.loading())
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(dbSource)
            result.removeSource(apiResponse)

            response?.apply {
                if (status.isSuccessful()) {

                        processResponse(this)

                } else {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        result.setValue(Resource.error(errorMessage))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType?>) {
        if (result.value != newValue) result.value = newValue
    }

    private fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType?>> {
        return result
    }

    @WorkerThread
    private fun processResponse(response: Resource<RequestType>): RequestType? {
        return response.data
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<Resource<RequestType>>
}