package uz.coder.tableprojecttest

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import uz.coder.tableprojecttest.databinding.ActivityMainBinding
import uz.coder.tableprojecttest.databinding.AddItemBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var list: ArrayList<User>
    private lateinit var adapter: AdapterTableRow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list = ArrayList()
        adapter = AdapterTableRow(list)
        binding.recyclerView.adapter = adapter
        binding.addBtn.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val addItemBinding = AddItemBinding.inflate(layoutInflater)
            addTextVatcher(addItemBinding)
            dialog.setView(addItemBinding.root).apply {
                addItemBinding.apply {
                    saveBtn.setOnClickListener {
                        val name = editeName.text.toString()
                        val age = editeAge.text.toString()
                        val job = editeJob.text.toString()
                        if (name.isNotEmpty() && age.isNotEmpty() && job.isNotEmpty() && age.toInt() > 0 && age.toInt() <= 100) {
                            val user = User(name, age.toInt(), job)
                            list.add(user)
                            adapter.notifyItemInserted(list.size)
                            dialog.dismiss()
                        } else {
                            if (name.isEmpty()) {
                                lyName.error = "Ism kiritilishi lozim"
                            }
                            if (age.isEmpty()) {
                                lyAge.error = "Yoshini kiriting"
                            } else if (age.toInt() < 0) {
                                lyAge.error = "Yoshi 0 dan katta bo'lishi shart"
                            } else if (age.toInt() > 100) {
                                lyAge.error = "Yoshi 100 yoki undan kichik bo'lishi shart"
                            }
                            if (job.isEmpty()) {
                                lyJob.error = "Kasbi kiritilishi shart"
                            }
                        }
                    }

                }
                dialog.show()
            }
        }

    }

    private fun addTextVatcher(addItemBinding: AddItemBinding) {
        addItemBinding.apply {
            editeAge.addTextChangedListener {
                lyAge.error = null
            }
            editeName.addTextChangedListener {
                lyName.error = null
            }
            editeJob.addTextChangedListener {
                lyJob.error = null
            }
        }
    }

}