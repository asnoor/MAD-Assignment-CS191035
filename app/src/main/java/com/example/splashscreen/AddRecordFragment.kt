package com.example.splashscreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.splashscreen.databinding.FragmentAddRecordsBinding

import com.example.splashscreen.model.datamodels.Record
import com.example.splashscreen.model.datamodels.RecordsModel
import kotlin.random.Random

class AddRecordFragment : Fragment() {
    private var _binding: FragmentAddRecordsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addRecordViewModel =
            ViewModelProvider(this)[AddRecordViewModel::class.java]
        val recordsModel = ViewModelProvider(requireActivity())[RecordsModel::class.java]

        _binding = FragmentAddRecordsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addRecordImage: EditText = binding.addRecordImage
        addRecordImage.doAfterTextChanged {
            var newUrl = it?.toString()
            if (newUrl == "") {
                newUrl = null
            }
            addRecordViewModel.image = newUrl
        }
        val addRecordName: EditText = binding.addRecordName
        addRecordName.doAfterTextChanged {
            addRecordViewModel.name = it?.toString() ?: ""
        }
        val addRecordDescription: EditText = binding.addRecordDescription
        addRecordDescription.doAfterTextChanged {
            addRecordViewModel.description = it?.toString() ?: ""
        }
        val addRecordButton: Button = binding.addRecordButton
        addRecordButton.setOnClickListener {
            val addImage = binding.addRecordImage
            val addName = binding.addRecordName
            val addDescription = binding.addRecordDescription
            if (addRecordViewModel.name.isNotBlank()) {
                val newRecord = Record(
                    Random.nextInt(0, 10000000),
                    addRecordViewModel.name,
                    addRecordViewModel.description,
                    addRecordViewModel.image,
                    false,
                );
                recordsModel.addRecord(newRecord, context!!)
                addImage.setText("")
                addName.setText("")
                addDescription.setText("")
            }
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}