package com.zxx.skyweather.ui.place

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zxx.skyweather.R
import com.zxx.skyweather.databinding.ChoosePlaceBindingImpl
import com.zxx.skyweather.ui.MainActivity

import com.zxx.skyweather.ui.weather.WeatherActivity
import com.zxx.skyweather.util.InjectorUtil
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.choose_place.*



class ChoosePlaceFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this, InjectorUtil.getChoosePlaceModelFactory()).get(ChoosePlaceViewModel::class.java) }
    private var progressDialog: ProgressDialog? = null
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.choose_place, container, false)
             val binding = DataBindingUtil.bind<ChoosePlaceBindingImpl>(view)
             binding?.viewModel = viewModel
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ChoosePlaceAdapter(context!!, R.layout.simple_item, viewModel.dataList)
        listView.adapter = adapter
        observe()
    }

    private fun observe() {
        viewModel.currentLevel.observe(this, Observer { level ->
            when (level) {
                LEVEL_PROVINCE -> {
                    titleText.text = "中国"
                    backButton.visibility = View.GONE
                }
                LEVEL_CITY -> {
                    titleText.text = viewModel.selectedProvince?.provinceName
                    backButton.visibility = View.VISIBLE
                }
                LEVEL_COUNTY -> {
                    titleText.text = viewModel.selectedCity?.cityName
                    backButton.visibility = View.VISIBLE
                }
            }
        })
        viewModel.dataChanged.observe(this, Observer {
            adapter.notifyDataSetChanged()
            listView.setSelection(0)
            closeProgressDialog()
        })
        viewModel.isLoading.observe(this, Observer { isLoading -> if (isLoading) showProgressDialog() else closeProgressDialog() })
        viewModel.placeSelected.observe(this, Observer { selected ->
            if (selected && viewModel.selectedCounty != null) {
                if (activity is MainActivity) {
                    val intent = Intent(activity, WeatherActivity::class.java)
                    intent.putExtra("weather_id", viewModel.selectedCounty!!.weatherId)
                    startActivity(intent)
                    activity?.finish()
                } else if (activity is WeatherActivity) {
                    val weatherActivity = activity as WeatherActivity
                    weatherActivity.drawerLayout.closeDrawers()
                    weatherActivity.viewModel.weatherId = viewModel.selectedCounty!!.weatherId
                    weatherActivity.viewModel.refreshWeather()
                }
                viewModel.placeSelected.value = false
            }
        })
        if (viewModel.dataList.isEmpty()) {
            viewModel.getProvinces()
        }
    }

    /**
     * 显示进度对话框
     */
    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(activity)
            progressDialog?.setMessage("正在加载...")
            progressDialog?.setCanceledOnTouchOutside(false)
        }
        progressDialog?.show()
    }

    /**
     * 关闭进度对话框
     */
    private fun closeProgressDialog() {
        progressDialog?.dismiss()
    }

    companion object {
        const val LEVEL_PROVINCE = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }

}
