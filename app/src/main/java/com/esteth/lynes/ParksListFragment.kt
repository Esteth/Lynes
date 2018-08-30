package com.esteth.lynes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.esteth.lynes.data.LynesDatabase
import com.esteth.lynes.data.Park
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_parks_list.*
import kotlinx.android.synthetic.main.list_item_park.*


/**
 * A simple [Fragment] subclass.
 * Use the [ParksListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ParksListFragment : Fragment() {

  val database: LynesDatabase by lazy {
    Room.databaseBuilder(context!!.applicationContext,
        LynesDatabase::class.java, "lynes_database")
        .build()
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_parks_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val adapter = ParkAdapter()
    parksList.adapter = adapter

    val observer = object : Observer<List<Park>> {
      override fun onChanged(parks: List<Park>?) {
        adapter.submitList(parks)
      }
    }
    database.parkDao().parks().observe(this, observer)
  }
}

class ParkAdapter : ListAdapter<Park, ParkAdapter.ParkViewHolder>(Park.DiffCallback) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder =
      ParkViewHolder(
          LayoutInflater.from(parent.context)
              .inflate(R.layout.list_item_park, parent, false))

  override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class ParkViewHolder(override val containerView: View) :
      RecyclerView.ViewHolder(containerView),
      LayoutContainer {
    fun bind(park: Park) {
      parkName.text = park.name
    }
  }
}
