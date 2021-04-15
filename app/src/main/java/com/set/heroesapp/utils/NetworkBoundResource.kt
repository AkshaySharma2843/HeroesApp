package com.set.heroesapp.utils



import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData


/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<RequestType> @MainThread constructor() {

    /**
     * The final result LiveData
     * MediatorLiveData is a LiveData subclass which may observe
     * other LiveData objects and react on OnChanged events from them.
     *
     * This class correctly propagates its active/inactive states down to source LiveData objects.
     */
    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        // Send loading state to UI
        result.value = Resource.loading()
        fetchFromNetwork()
    }

    /**
     * Fetch the data from network and then send it upstream to UI.
     */
    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        // Make the network call
        result.addSource(apiResponse) { response ->

            result.removeSource(apiResponse)
            // Dispatch the result
            response?.apply {
                when {
                    // Can be done with if statement if status is successful set this otherwise set error message
                    status.isSuccessful() -> {
                        setValue(this)
                    }
                    else -> {
                        setValue(Resource.error(errorMessage))
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<RequestType>> {
        return result
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue)
            result.value = newValue
    }

    @MainThread
    protected abstract fun createCall(): LiveData<Resource<RequestType>>
}