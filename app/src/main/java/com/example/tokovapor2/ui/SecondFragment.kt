package com.example.tokovapor2.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tokovapor2.R
import com.example.tokovapor2.aplication.VaporsApp
import com.example.tokovapor2.databinding.FragmentSecondBinding
import com.example.tokovapor2.model.Vapors

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val vaporsViewModel: VaporsViewModel by viewModels {
        VaporViewModelFactory((applicationContext as VaporsApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var vapors: Vapors? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vapors = args.vapors
//kita cek
        if (vapors != null) {
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "UBAH"
            binding.nameEditText.setText(vapors?.name)
            binding.addressEditTextText2.setText(vapors?.address)
            binding.goodsEditTextText3.setText(vapors?.goods)
            binding.typeEditTextText.setText(vapors?.type)
            binding.amountgoodsEditTextText2.setText(vapors?.amountgoods)
        }
        val name = binding.nameEditText.text
        val address = binding.addressEditTextText2.text
        val goods = binding.goodsEditTextText3.text
        val type = binding.typeEditTextText.text
        val amountgoods = binding.amountgoodsEditTextText2.text
        binding.saveButton.setOnClickListener {
            if (name.isEmpty() ){
                Toast.makeText(context, "Nama Tidak Boleh kosong", Toast.LENGTH_SHORT).show()

                } else if (address.isEmpty() ){
                 Toast.makeText(context, "Alamat Tidak Boleh kosong", Toast.LENGTH_SHORT).show()

                } else {
                if (vapors == null) {
                    val vapors = Vapors(0, name.toString(), address.toString(),
                        goods.toString(), type.toString(), amountgoods.toString())
                    vaporsViewModel.insert(vapors)

                } else {
                    val vapors = Vapors(vapors?.id!!, name.toString(), address.toString(),
                        goods.toString(), type.toString(), amountgoods.toString())
                    vaporsViewModel.update(vapors)

                }
                val vapors = Vapors(0, name.toString(), address.toString(),
                    goods.toString(), type.toString(), amountgoods.toString())
                vaporsViewModel.insert(vapors)
                findNavController().popBackStack() // untuk dismiss halaman ini
            }

        }

        binding.deleteButton.setOnClickListener {
                vapors?.let { vaporsViewModel.delete(it) }
                findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}