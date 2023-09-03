package com.arrows.softxperttask.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrows.softxperttask.R
import com.arrows.softxperttask.adapter.AnimalsAdapter
import com.arrows.softxperttask.adapter.FooterLoadStateAdapter
import com.arrows.softxperttask.adapter.TypesAdapter
import com.arrows.softxperttask.databinding.ActivityMainBinding
import com.arrows.softxperttask.viewModels.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private val viewModel: HomePageViewModel by lazy { ViewModelProvider(this)[HomePageViewModel::class.java] }
    private val layoutManeger = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animalsAdapter = AnimalsAdapter();

        val typesAdapter = TypesAdapter();


        layoutManeger.orientation = LinearLayoutManager.HORIZONTAL
        lifecycleScope.launch {
            viewModel.animals.collectLatest {
                if (it != null) {
                    animalsAdapter.submitData(it)
                }

            }
        }
        binding.animalRecycler.adapter = animalsAdapter.withLoadStateFooter(FooterLoadStateAdapter())

        lifecycleScope.launch {
            viewModel.animalTypes.collect {
                if (it.isNotEmpty()) {

                    typesAdapter.submitList(it)
                    binding.animalTypesRecycler.layoutManager = layoutManeger
                    binding.animalTypesRecycler.adapter = typesAdapter

                }

            }
        }

        animalsAdapter.onItemClick = {
            val intent = Intent(this, AnimalDetails::class.java)
            intent.putExtra("AnimalId", it)
            startActivity(intent)
        }

        typesAdapter.onItemClick = {
            if (it.isNullOrBlank()) {
                viewModel.getAnimalList()
            } else {
                Log.d("hesham", "types here " + it)
                viewModel.getAnimalList(it)
            }
        }

        animalsAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    binding.loading.show()
                }
                else -> {
                    binding.loading.hide()
                }
            }
        }


    }
}