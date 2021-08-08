package sm_tv_prodactions.com.weatherincities

import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import sm_tv_prodactions.com.weatherincities.utils.Constants
import sm_tv_prodactions.com.weatherincities.utils.Constants.PERMISSION1
import sm_tv_prodactions.com.weatherincities.utils.Constants.PERMISSION2

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }



}