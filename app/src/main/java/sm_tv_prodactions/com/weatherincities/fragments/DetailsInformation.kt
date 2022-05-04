package sm_tv_prodactions.com.weatherincities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import sm_tv_prodactions.com.weatherincities.R
import sm_tv_prodactions.com.weatherincities.dataRest.repository.Repository
import sm_tv_prodactions.com.weatherincities.dataRest.viewModel.MainViewModel
import sm_tv_prodactions.com.weatherincities.dataRest.viewModel.MainViewModelFactory
import sm_tv_prodactions.com.weatherincities.utils.Constants.TOKEN_ID


class DetailsInformation : Fragment() {

    private val args by navArgs<DetailsInformationArgs>()
    private lateinit var mViewModel: MainViewModel

    private lateinit var tvCitiName: TextView
    private lateinit var tvTempDetails: TextView
    private lateinit var tvTempFeels_like: TextView
    private lateinit var tvSpeed: TextView
    private lateinit var imWeather: ImageView
    private lateinit var imStrelka: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_datails_information, container, false)

        //viewmodel provider
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        mViewModel.getCites(args.citiName, TOKEN_ID)
        chekResponse()

        //init elements
        tvCitiName = view.findViewById(R.id.tvCitiName)
        tvTempDetails = view.findViewById(R.id.tvTempDetails)
        tvTempFeels_like = view.findViewById(R.id.tvTempFeels_like)
        tvSpeed = view.findViewById(R.id.tvSpeed)
        imWeather = view.findViewById(R.id.imWeather)
        imStrelka = view.findViewById(R.id.imStrelka)


        //name tolbar
        val str = resources.getString(R.string.detail_fragment) + " " + args.citiName
        (activity as AppCompatActivity).supportActionBar?.title = str


        return view
    }

    private  fun chekResponse(){
        mViewModel.myResponseListCities.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                val str = response.body()
                tvCitiName.text = str?.name
                val path = str?.weather?.get(0)?.icon
                if (path != null) {
                    displayIcon(path)
                }
                val temp = (str?.main?.temp?.minus(273.15))?.toInt()
                val temp_feels_like = (str?.main?.feels_like?.minus(273.15))?.toInt()
                val deg = str?.wind?.deg
                val speed = str?.wind?.speed
                tvTempDetails.text =temp.toString()
                tvTempFeels_like.text =temp_feels_like.toString()

                deg?.let { imStrelka.rotation = it.toFloat() }
                val text = "u=${speed} м/с"
                tvSpeed.text = text

            } else {
                Toast.makeText(
                    requireContext(),
                    response.message().toString() + response.code().toString() + response.body()
                        .toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
    private fun displayIcon(path: String){
        val str = "https://openweathermap.org/img/wn/${path}@2x.png"
        Picasso.get().load(str).into(imWeather)
    }

}