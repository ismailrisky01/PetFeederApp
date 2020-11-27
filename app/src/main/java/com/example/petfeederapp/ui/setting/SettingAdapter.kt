package com.example.petfeederapp.ui.setting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petfeederapp.R
import com.example.petfeederapp.model.DeviceData
import kotlinx.android.synthetic.main.item_setting.view.*

class SettingAdapter(val context: Context) : RecyclerView.Adapter<SettingAdapter.ViewHolder>() {
    private var dataList = mutableListOf<DeviceData>()
    fun setListData(settingData: MutableList<DeviceData>) {
        dataList = settingData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val addItem = layoutInflater.inflate(R.layout.item_setting, parent, false)
        return ViewHolder(
            addItem
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = dataList[position]
        holder.bindView(device)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id = String()
        fun bindView(data: DeviceData) {
            id = data.id
            itemView.txtSetting.text = data.name
        }

        init {
            view.setOnClickListener {
                val bundle = bundleOf()
                bundle.putString("id", id)
                it.findNavController()
                    .navigate(R.id.action_navigation_notifications_to_detailSettingFragment,bundle)
            }
        }
    }
}