package com.example.testing.view.mainactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testing.databinding.FragmentAllBinding
import com.example.testing.viewmodel.MainActivityViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllFragment(private val homeActivityViewModel: MainActivityViewModel) : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentAllBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        homeActivityViewModel.getPostsV({

        }, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        return binding?.root
    }
}