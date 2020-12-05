package com.example.petfeederapp.ui.setting

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petfeederapp.R
import com.example.petfeederapp.library.BaseActivity
import com.example.petfeederapp.library.OptionMenu
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.coroutines.GlobalScope

class SettingFragment : OptionMenu() {
    private val settingMainView by lazy {
        ViewModelProvider(this).get(SettingViewModel::class.java)
    }


    private lateinit var adapter: SettingAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val root = inflater.inflate(R.layout.fragment_setting, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.apply {
            adapter =
                SettingAdapter(requireContext())
            recycleViewSetting.layoutManager = LinearLayoutManager(requireContext())
            recycleViewSetting.adapter = adapter
            observe()
        }
    }
     fun observe() {
        settingMainView.getDeviceData().observe(viewLifecycleOwner, Observer {data->
            hideProgressBar()
            adapter.setListData(data)
            adapter.notifyDataSetChanged()
        })
    }

}