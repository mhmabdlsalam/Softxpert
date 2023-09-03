package com.arrows.softxperttask.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arrows.domain.paging.AnimalsPagingSource
import com.arrows.domain.model.Animal
import com.arrows.domain.usecase.GetAnimalTypes
import com.arrows.domain.usecase.GetAnimals
import com.arrows.domain.usecase.GetAnimalsByType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAnimalsUseCase : GetAnimals,private val getAnimalsByTypeUseCase : GetAnimalsByType
    , private val getAnimalTypesUseCase: GetAnimalTypes
) : ViewModel() {

    private var _animals: MutableStateFlow<PagingData<Animal>?> = MutableStateFlow(null)
    val animals: StateFlow<PagingData<Animal>?> = _animals

    private val _animalTypes: MutableStateFlow<List<String>> = MutableStateFlow(emptyList());
    val animalTypes: StateFlow<List<String>> = _animalTypes

    private val error = CoroutineExceptionHandler { _, throwable ->

        throwable.printStackTrace()
    }

    init {
        getAnimalList()
        getAnimalTypesList()
    }

    fun getAnimalList() {
        viewModelScope.launch(error + Dispatchers.Main) {
            Pager(
                config = PagingConfig(pageSize = 20,
                    initialLoadSize = 20,
                    enablePlaceholders = false,
                    prefetchDistance = 2 ) ,
                pagingSourceFactory = {getAnimalsUseCase()} ,
                initialKey = 1
            ).flow.cachedIn(this).collect{
                _animals.value = it
            }
        }

    }

    fun getAnimalList(type:String) {
        Log.d("hesham","viewModel")
        viewModelScope.launch(error + Dispatchers.Main) {
            Pager(
                config = PagingConfig(pageSize = 20,
                    initialLoadSize = 20,
                    enablePlaceholders = false,
                    prefetchDistance = 2 ) ,
                pagingSourceFactory = {getAnimalsByTypeUseCase(type)} ,
                initialKey = 1
            ).flow.cachedIn(this).collect{
                _animals.value = it

            }
        }

    }

    private fun getAnimalTypesList() {
        viewModelScope.launch(error + Dispatchers.Main) {
            _animalTypes.value = getAnimalTypesUseCase()
        }

    }


}