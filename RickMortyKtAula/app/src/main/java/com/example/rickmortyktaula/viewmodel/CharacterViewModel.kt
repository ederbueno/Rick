package com.example.rickmortyktaula.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rickmortyktaula.model.Result
import com.example.rickmortyktaula.repository.RepositoryRickMorty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    val listMutableCharacter = MutableLiveData<MutableSet<Result>>()
    private val repository = RepositoryRickMorty()

    fun getAllCharacters() = CoroutineScope(IO).launch {
        repository.getCharacterService().let { charactersResponse ->
            listMutableCharacter.postValue(charactersResponse.results)
        }
    }

}