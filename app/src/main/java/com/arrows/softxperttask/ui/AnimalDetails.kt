package com.arrows.softxperttask.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.arrows.softxperttask.R
import com.arrows.softxperttask.databinding.ActivityAnimalDetailsBinding
import com.arrows.softxperttask.viewModels.AnimalDetailsViewModel
import com.arrows.softxperttask.viewModels.HomePageViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimalDetails : AppCompatActivity() {
    private lateinit var binding : ActivityAnimalDetailsBinding
    private val viewModel: AnimalDetailsViewModel by lazy { ViewModelProvider(this)[AnimalDetailsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("AnimalId",0)
        binding.loading.show()
        viewModel.getAnimalDetails(id)

        lifecycleScope.launch {
            viewModel.animal.collect {
                if (it!=null) {
                    binding.loading.hide()
                    var url :String = ""
                    binding.apply {

                        if (it.animal.photos.isNullOrEmpty()||it.animal.photos[0].medium.isNullOrEmpty()){
                            Picasso.get().load(R.drawable.placeholder).fit()
                                .into(animalPhoto)
                        }else{
                            Picasso.get().load(it.animal.photos[0].medium).fit()
                                .error(R.drawable.placeholder).fit()
                                .into(animalPhoto)
                        }

                        if (it.animal.name.isNullOrEmpty()){
                            animalName.text = "NA"
                        }else{
                            animalName.text = it.animal.name
                        }
                        if (it.animal.colors==null||it.animal.colors.primary.isNullOrEmpty()){
                            animalColor.text = "NA"
                        }else{
                            animalColor.text = it.animal.colors.primary
                        }

                        if (it.animal.contact==null||it.animal.contact.address==null
                            ||it.animal.contact.address.city.isNullOrEmpty()){
                            animalAddress.text = "NA"
                        }else{
                            animalAddress.text = "${it.animal.contact.address.city} , ${it.animal.contact.address.state}" +
                                    ", ${it.animal.contact.address.country}"
                        }

                        if (it.animal.size.isNullOrEmpty()){
                            animalSize.text = "NA"
                        }else{
                            animalSize.text = it.animal.size
                        }

                        if (it.animal.url.isNotEmpty()){
                            url = it.animal.url
                        }

                        animalWebsiteBtn.setOnClickListener {
                            val openURL = Intent(Intent.ACTION_VIEW)
                            openURL.data = Uri.parse(url)
                            startActivity(openURL)
                        }

                    }

                }

            }
        }




        Log.d("hesham ", "animal id $id")

    }
}