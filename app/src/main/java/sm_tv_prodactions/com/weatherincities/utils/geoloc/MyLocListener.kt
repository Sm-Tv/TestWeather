package sm_tv_prodactions.com.weatherincities.utils.geoloc

import android.location.Location
import android.location.LocationListener
import android.os.Bundle


class MyLocListener: LocationListener {

    private lateinit var locListenerInterface: LocListener

    override fun onLocationChanged(location: Location) {
        locListenerInterface.onLocationChanged(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    fun setLocListenerInterface(locListenerInterface: LocListener){
        this.locListenerInterface = locListenerInterface
    }




}