package sm_tv_prodactions.com.weatherincities.adapters


import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_citi.view.*
import sm_tv_prodactions.com.weatherincities.R
import sm_tv_prodactions.com.weatherincities.models.ModelCities

class MyAdapter: RecyclerView.Adapter<MyAdapter.myViewHolder>() {

    private var lat: Double = 0.0
    private var lon: Double = 0.0

    private var sortedList = SortedList(ModelCities::class.java, object : SortedList.Callback<ModelCities>() {
        override fun compare(o1: ModelCities, o2: ModelCities): Int {

            if (o1.coord.lat != lat && o1.coord.lon != lon) {
                print("+++++++++++++++++++++++++++")
                return 1
            } else  {
                return -1
            }
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun areContentsTheSame(oldItem: ModelCities, newItem: ModelCities): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(item1: ModelCities, item2: ModelCities): Boolean {
            return item1.name == item2.name
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }
    })


    class myViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //private lateinit var  mViewModel: NoteViewModels
        private lateinit var note: ModelCities

        fun bind(noteer: ModelCities){
            note = noteer
            val str = "https://openweathermap.org/img/wn/${note.weather[0].icon}@2x.png"
            Picasso.get().load(str).into(itemView.imIconId)
            itemView.tvTextName.text = note.name
            val temp = (note.main.temp.toDouble() - 273.15).toInt()
            itemView.tvTextTemp.text = temp.toString()
            itemView.setOnClickListener{
                val bundle = Bundle()
                bundle.putString("citiName", note.name)
                itemView.findNavController().navigate(R.id.action_addCities_to_datailsInformation, bundle)
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return MyAdapter.myViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_citi, parent, false)
        )
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        sortedList.let { holder.bind(it.get(position)) }
    }

    override fun getItemCount(): Int {
        return sortedList.size()
    }

    fun setItems(notes: ArrayList<ModelCities>, lat: Double, lon:Double) {
        sortedList.replaceAll(notes)
        this.lat = lat
        this.lon = lon
    }


}


