package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculator.databinding.FragmentNumbersBinding


class NumbersFragment : Fragment() {
    private val  dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentNumbersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumbersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonZero.setOnClickListener {
            dataModel.message.value = "0"
        }
        binding.buttonOne.setOnClickListener {
            dataModel.message.value = "1"
        }
        binding.buttonTwo.setOnClickListener {
            dataModel.message.value = "2"
        }
        binding.buttonThree.setOnClickListener {
            dataModel.message.value = "3"
        }
        binding.buttonFour.setOnClickListener {
            dataModel.message.value = "4"
        }
        binding.buttonFive.setOnClickListener {
            dataModel.message.value = "5"
        }
        binding.buttonSix.setOnClickListener {
            dataModel.message.value = "6"
        }
        binding.buttonSeven.setOnClickListener {
            dataModel.message.value = "7"
        }
        binding.buttonEight.setOnClickListener {
            dataModel.message.value = "8"
        }
        binding.buttonNine.setOnClickListener {
            dataModel.message.value = "9"
        }
        binding.buttonDot.setOnClickListener {
            dataModel.message.value = "."
        }
        binding.buttonPlus.setOnClickListener {
            dataModel.message.value = "+"
        }
        binding.buttonMinus.setOnClickListener {
            dataModel.message.value = "−"
        }
        binding.buttonMultiply.setOnClickListener {
            dataModel.message.value = "×"
        }
        binding.buttonDivision.setOnClickListener {
            dataModel.message.value = "÷"
        }
        binding.buttonBrace1.setOnClickListener {
            dataModel.message.value = "("
        }
        binding.buttonBrace2.setOnClickListener {
            dataModel.message.value = ")"
        }
        binding.buttonClear.setOnClickListener {
            dataModel.message.value = "clear"
        }
        binding.buttonDelete.setOnClickListener {
            dataModel.message.value = "delete"
        }
        binding.buttonEquals.setOnClickListener {
            dataModel.message.value = "equals"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NumbersFragment()
    }
}

