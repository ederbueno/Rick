package com.example.rickmortykt.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortykt.model.Result
import com.example.rickmortykt.repository.RepositoryRickMorty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ViewModelCharacter : ViewModel() {
    val listCharacter = MutableLiveData<List<Result>>()
    private val repositoryRickMorty = RepositoryRickMorty()

    fun getAllCharacters() = CoroutineScope(IO).launch {
        repositoryRickMorty.getServiceRick().let {
            listCharacter.postValue(it.results)
            }
        }
}
