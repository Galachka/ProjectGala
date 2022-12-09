package com.example.projectgala.ui.Fragments

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.projectgala.Model.Notes
import com.example.projectgala.R
import com.example.projectgala.ViewModel.NotesViewModel
import com.example.projectgala.databinding.FragmentEditNotesBinding
import com.example.projectgala.databinding.ItemNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {

    val oldnotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    val viewModel : NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        binding.editTitle.setText(oldnotes.data.title)
        binding.editSubtitle.setText(oldnotes.data.subtitle)
        binding.editNotes.setText(oldnotes.data.notes)

        when(oldnotes.data.priority){
            "1" ->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" ->{
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" ->{
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }
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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.getTime())
        val data = Notes(oldnotes.data.id,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority

        )

        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Notes Edited Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!)
            .navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val texviewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val texviewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            texviewYes?.setOnClickListener {
                viewModel.deleteNotes(oldnotes.data.id!!)
                bottomSheet.dismiss()
                this.findNavController()
                    .navigate(R.id.action_editNotesFragment_to_homeFragment)

            }
            texviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}