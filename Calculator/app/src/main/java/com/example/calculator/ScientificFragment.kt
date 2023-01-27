package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculator.databinding.FragmentNumbersBinding
import com.example.calculator.databinding.FragmentScientificBinding

class ScientificFragment : Fragment() {



    private val  dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentScientificBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScientificBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonAbs.setOnClickListener {
            dataModel.message.value = "!"
        }
        binding.buttonPi.setOnClickListener {
            dataModel.message.value = "pi"
        }
        binding.buttonEpsilon.setOnClickListener {
            dataModel.message.value = "e"
        }
        binding.buttonEpowerX.setOnClickListener {
            dataModel.message.value = "e^x"
        }
        binding.buttonXSquare.setOnClickListener {
            dataModel.message.value = "x^2"
        }
        binding.buttonXPowerY.setOnClickListener {
            dataModel.message.value = "x^y"
        }
        binding.buttonLn.setOnClickListener {
            dataModel.message.value = "ln"
        }
        binding.buttonLog.setOnClickListener {
            dataModel.message.value = "log"
        }
        binding.buttonSin.setOnClickListener {
            dataModel.message.value = "sin"
        }
        binding.buttonCos.setOnClickListener {
            dataModel.message.value = "cos"
        }
        binding.buttonTan.setOnClickListener {
            dataModel.message.value = "tan"
        }
        binding.buttonTwoPowerX.setOnClickListener {
            dataModel.message.value = "2^x"
        }
        binding.buttonRoot.setOnClickListener {
            dataModel.message.value = "√"
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = ScientificFragment()
    }
}