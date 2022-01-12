package com.mrzhgn.rickandmortytest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrzhgn.rickandmortytest.R
import com.mrzhgn.rickandmortytest.adapters.ListAdapter
import com.mrzhgn.rickandmortytest.databinding.FragmentMainBinding
import com.mrzhgn.rickandmortytest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), ListAdapter.ListAdapterListener {

    private lateinit var list: MutableList<ListItem>
    private lateinit var adapter: ListAdapter
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeData()
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(v: RecyclerView, state: Int) {
                super.onScrollStateChanged(v, state)

                if (!v.canScrollVertically(1) && state == RecyclerView.SCROLL_STATE_IDLE) {
                    mainViewModel.incrementPage()
                }
            }
        })
    }

    override fun onItemClick(person: ListItem?) {
        val args = Bundle()
        args.putInt("id", person?.person?.id ?: 0)
        (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_personFragment, args)
    }

    private fun initRecyclerView() {
        list = ArrayList()
        binding.recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ListAdapter(list, this)
        binding.recyclerview.adapter = adapter
    }

    private fun observeData() {
        mainViewModel.listLiveData.observe(viewLifecycleOwner, { list ->
            val personList = list.map { ListItem(it) }.toList()
            adapter.updateList(personList)
        })
    }
}