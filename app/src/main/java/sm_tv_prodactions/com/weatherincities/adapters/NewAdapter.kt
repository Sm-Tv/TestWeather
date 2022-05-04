package sm_tv_prodactions.com.weatherincities.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import sm_tv_prodactions.com.weatherincities.R
import sm_tv_prodactions.com.weatherincities.models.ModelCities

class NewAdapter: RecyclerView.Adapter<NewAdapter.MyViewHolder>() {

    private var userList = emptyList<ModelCities>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val citiName = itemView.findViewById<TextView>(R.id.tvTextName)
        val citiIcon = itemView.findViewById<ImageView>(R.id.imIconId)
        val citiTemp = itemView.findViewById<TextView>(R.id.tvTextTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_citi, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val courentItem = userList[position]
        val str = "https://openweathermap.org/img/wn/${courentItem.weather[0].icon}@2x.png"
        Picasso.get().load(str).into(holder.citiIcon)
        holder.citiName.text = courentItem.name
        val temp = (courentItem.main.temp - 273.15).toInt()
        holder.citiTemp.text = temp.toString()
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("citiName", courentItem.name)
            holder.itemView.findNavController().navigate(R.id.action_addCities_to_datailsInformation, bundle)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: ArrayList<ModelCities>) {
        this.userList = user
        notifyDataSetChanged()
    }
}