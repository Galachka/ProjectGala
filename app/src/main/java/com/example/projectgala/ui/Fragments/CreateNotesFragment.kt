package com.example.projectgala.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectgala.R
import com.example.projectgala.databinding.FragmentCreateNotesBinding
import java.util.Date


class CreateNotesFragment : Fragment() {
    lateinit var binding: FragmentCreateNotesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setOnClickListener{
            binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener{
            binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener{
            binding.pRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text
        val subTitle = binding.editSubtitle.text
        val notes = binding.editNotes.text
        val d = Date()
        val s: CharSequence = DateFormat.format("MMMM d, yyyy", d.getTime())


    }
}