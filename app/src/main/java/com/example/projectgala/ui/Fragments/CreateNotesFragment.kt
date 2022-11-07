package com.example.projectgala.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.projectgala.Model.Notes
import com.example.projectgala.R
import com.example.projectgala.ViewModel.NotesViewModel
import com.example.projectgala.databinding.FragmentCreateNotesBinding
import java.util.Date


class CreateNotesFragment : Fragment() {
    lateinit var binding: FragmentCreateNotesBinding
    val viewModel : NotesViewModel by viewModels()
    var priority: String = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)

        binding.pGreen.setOnClickListener{
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener{
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener{
            priority = "3"
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
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.getTime())
        val data = Notes(null,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority

        )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Notes Greated Successfully", Toast.LENGTH_SHORT).show()
    }
}