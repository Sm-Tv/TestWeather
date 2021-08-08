package sm_tv_prodactions.com.weatherincities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_citi.view.*
import sm_tv_prodactions.com.weatherincities.R
import sm_tv_prodactions.com.weatherincities.models.Citi
import sm_tv_prodactions.com.weatherincities.models.ModelCities

class NewAdapter: RecyclerView.Adapter<NewAdapter.myViewHolder>() {

    private var userList = emptyList<ModelCities>()


    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val citiName = itemView.findViewById<TextView>(R.id.tvTextName)
        val citiIcon = itemView.findViewById<ImageView>(R.id.imIconId)
        val citiTemp = itemView.findViewById<TextView>(R.id.tvTextTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_citi, parent, false)
        )
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val courentItem = userList[position]
        val str = "https://openweathermap.org/img/wn/${courentItem.weather[0].icon}@2x.png"
        Picasso.get().load(str).into(holder.citiIcon)
        holder.citiName.text = courentItem.name
        val temp = (courentItem.main.temp.toDouble() - 273.15).toInt()
        holder.citiTemp.text = temp.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: ArrayList<ModelCities>) {
        this.userList = user
        notifyDataSetChanged()
    }
}