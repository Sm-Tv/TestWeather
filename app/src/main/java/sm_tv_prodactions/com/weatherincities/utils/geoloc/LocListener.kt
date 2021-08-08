package sm_tv_prodactions.com.weatherincities.utils.geoloc

import android.location.Location

interface LocListener {

    fun onLocationChanged(location: Location)

}