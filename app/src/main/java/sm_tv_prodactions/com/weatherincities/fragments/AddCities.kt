package sm_tv_prodactions.com.weatherincities.fragments


import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_add_cities.view.*
import sm_tv_prodactions.com.weatherincities.MainActivity
import sm_tv_prodactions.com.weatherincities.R
import sm_tv_prodactions.com.weatherincities.adapters.NewAdapter
import sm_tv_prodactions.com.weatherincities.dataBD.Citi
import sm_tv_prodactions.com.weatherincities.dataBD.viewmodel.ViewModelDb
import sm_tv_prodactions.com.weatherincities.dataRest.viewModel.MainViewModel
import sm_tv_prodactions.com.weatherincities.models.ModelCities
import sm_tv_prodactions.com.weatherincities.utils.Constants.PERMISSION1
import sm_tv_prodactions.com.weatherincities.utils.Constants.PERMISSION2
import sm_tv_prodactions.com.weatherincities.utils.Constants.PERMISSION_REQIST
import sm_tv_prodactions.com.weatherincities.utils.Constants.TOKEN_ID
import sm_tv_prodactions.com.weatherincities.utils.geoloc.LocListener
import sm_tv_prodactions.com.weatherincities.utils.geoloc.MyLocListener

class AddCities : Fragment(), LocListener {


    private lateinit var mViewModel: MainViewModel
    private lateinit var mViewModelDb: ViewModelDb
    private lateinit var adapter: NewAdapter
    var citiList = ArrayList<ModelCities>()

    private lateinit var locationManager: LocationManager
    private lateinit var myLocListener: MyLocListener

    private var lat = "0.0"
    private var lon = "0.0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_cities, container, false)
        locationManager =
            (activity as MainActivity).getSystemService(Context.LOCATION_SERVICE) as LocationManager
        myLocListener = MyLocListener()
        myLocListener.setLocListenerInterface(this)
        setHasOptionsMenu(true)
        chekPermission(PERMISSION1, PERMISSION2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewAdapter()
        initRecycler(view, adapter)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        chekResponse()
        initViewModelDB()
        view.my_floatingActionButton.setOnClickListener {
            getNameCities()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                mViewModelDb.deleteAllNotes()
                citiList.clear()
                adapter.setData(citiList)
            }
        }
        return true
    }

    private fun initViewModelDB() {
        mViewModelDb = ViewModelProvider(this).get(ViewModelDb::class.java)
        mViewModelDb.readAllData.observe(viewLifecycleOwner, { note ->
            getData(note, TOKEN_ID)
        })
    }

    private fun initRecycler(view: View, adapter: NewAdapter) {
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.my_recyclerView)
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        myRecyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
    }

    private fun getNameCities() {
        val dialog = Dialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        val edText = dialogView.findViewById<EditText>(R.id.edDialogText)
        val pbutton = dialogView.findViewById<Button>(R.id.posButton)
        val nbutton = dialogView.findViewById<Button>(R.id.negButton)
        pbutton.setOnClickListener {
            val title = edText.text.toString().removeSuffix(" ")
            val citi = Citi(0, title)
            if (chekInput(title)) {
                mViewModelDb.addCiti(citi)
                dialog.dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.warning_dialog),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        nbutton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(dialogView)
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun getData(title: List<Citi>, key: String) {
        for (i in title) {
            mViewModel.getCites(i.name, key)
        }
    }

    private fun chekInput(title: String): Boolean {
        return !(TextUtils.isEmpty(title))
    }

    private fun chekPermission(permission1: String, permission2: String) {
        if (Build.VERSION.SDK_INT >= 23 &&
            checkSelfPermission(requireContext(), permission1) != PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(requireContext(), permission2) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(permission1, permission2),
                PERMISSION_REQIST
            )
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 1000.0F, myLocListener)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (checkSelfPermission(requireContext(), PERMISSION1) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(requireContext(), PERMISSION2) == PackageManager.PERMISSION_GRANTED
        ) {
            chekPermission(PERMISSION1, PERMISSION2)
        }
    }

    private fun coordinateQuery(lat: String, lon: String) {
        val later = lat.toDouble()
        val loner = lon.toDouble()
        mViewModel.getCitiesByCoord(later, loner, TOKEN_ID)
    }

    private fun chekResponse() {
        mViewModel.myResponseListCities.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                val str = response.body()
                str?.let { citiList.add(it) }
                val set: HashSet<ModelCities> = HashSet(citiList)
                citiList.clear()
                citiList.addAll(set)
                adapter.setData(citiList)
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

    override fun onLocationChanged(location: Location) {
        lat = "%.4f".format(location.latitude).replace(",", ".", true)
        lon = "%.4f".format(location.longitude).replace(",", ".", true)
        coordinateQuery(lat, lon)
    }
}
