package uz.coder.tableprojecttest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.coder.tableprojecttest.databinding.TalbleRowLayotBinding

class AdapterTableRow(val list: List<User>) : RecyclerView.Adapter<AdapterTableRow.VH>() {

    inner class VH(val binding: TalbleRowLayotBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        TalbleRowLayotBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            txtName.text = list[position].name
            txtAge.text = list[position].age.toString()
            txtJob.text = list[position].job
        }
    }


}