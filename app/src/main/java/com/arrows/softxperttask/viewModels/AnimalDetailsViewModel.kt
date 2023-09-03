package com.arrows.softxperttask.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.arrows.domain.model.Animal
import com.arrows.domain.model.AnimalDetailsResponse
import com.arrows.domain.usecase.GetAnimalDetails
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
class AnimalDetailsViewModel @Inject constructor(
    private val getAnimalDetailsUseCase : GetAnimalDetails
) : ViewModel()
{


    private var _animal: MutableStateFlow<AnimalDetailsResponse?> = MutableStateFlow(null)
    val animal: StateFlow<AnimalDetailsResponse?> = _animal


    private val error = CoroutineExceptionHandler { _, throwable ->

        throwable.printStackTrace()
    }

     fun getAnimalDetails(id:Int) {
        viewModelScope.launch(error + Dispatchers.Main) {
            _animal.value = getAnimalDetailsUseCase(id)
        }
    }

}