package com.example.first_kotlin_projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.first_kotlin_projects.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var isReverse: Boolean = false
    private var result: Int = 0
    private val btnPlus = "+1"
    private val btnMinus = "-1"

    private lateinit var resultFragment: ResultFragment
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        init()
        btnListener();
        return binding.root
    }

    private fun init() {
        binding.textView.text = result.toString();
        binding.button.text = btnPlus;
        resultFragment = ResultFragment();
        bundle = Bundle();
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()

    }

    private fun btnListener() {
        binding.button.setOnClickListener(View.OnClickListener {
            if (result == 10) {
                binding.button.text = btnMinus;
                isReverse = !isReverse
            } else if (isReverse && result == 0) {
                bundle.putInt(Keys.KEY_FOR_RESULT_MAIN_FRAGMENT, result)
                resultFragment.arguments = bundle
                fragmentTransaction.replace(R.id.container, resultFragment).addToBackStack(null)
                    .commit()
                result = -1
                isReverse = !isReverse
            }
            if (result != 10 && !isReverse) {
                result++;
            } else if (isReverse) {
                result--;
            }
            binding.textView.text = result.toString();
        })
    }
}