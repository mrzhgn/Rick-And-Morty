package com.mrzhgn.rickandmortytest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.mrzhgn.rickandmortytest.R
import com.mrzhgn.rickandmortytest.databinding.FragmentPersonBinding
import com.mrzhgn.rickandmortytest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment : Fragment(R.layout.fragment_person) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { mainViewModel.getCharacterInfo(it.getInt("id")) }
        initCharacter()
        binding.back.setOnClickListener{
            (activity as MainActivity).onBackPressed()
        }
    }

    private fun initCharacter() {
        mainViewModel.personLiveData.observe(viewLifecycleOwner, {
            it.image.let {
                Glide.with(context!!)
                    .load(it)
                    .centerCrop()
                    .into(binding.image)
            }
            binding.name.text = it.name
            binding.characterGender.text = it.gender
            binding.characterOrigin.text = it.originName
            binding.characterLocation.text = it.locationName
            binding.characterType.text = it.locationType ?: "unknown"
            binding.characterDimension.text = it.locationDimension ?: "unknown"
            binding.characterEpisodes.text = it.episodes.toString()
            binding.isAlive.setImageResource(when (it.status) {
                "Alive" -> R.drawable.circle_green
                else -> R.drawable.circle_red
            })
            binding.status.text = "${it.status} - ${it.species}"
        })
    }
}