package com.esteth.lynes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esteth.lynes.data.LynesDatabase
import com.esteth.lynes.data.Park
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_parks_list.*
import kotlinx.android.synthetic.main.list_item_park.*


/**
 * A [Fragment] for displaying a list of parks.
 */
class ParksListFragment : Fragment() {

  val database: LynesDatabase by lazy {
    LynesDatabase.getInstance(activity!!)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_parks_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    parksList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    val adapter = ParkAdapter()
    parksList.adapter = adapter

    database
        .parkDao()
        .parks()
        .observe(this, Observer<List<Park>> { parks -> adapter.submitList(parks) })
  }
}

class ParkAdapter : ListAdapter<Park, ParkAdapter.ParkViewHolder>(Park.DiffCallback) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder =
      ParkViewHolder(
          LayoutInflater.from(parent.context)
              .inflate(R.layout.list_item_park, parent, false))

  override fun onBindViewHolder(holder: ParkViewHolder, position: Int) =
      holder.bind(getItem(position))

  class ParkViewHolder(override val containerView: View) :
      RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(park: Park) {
      parkName.text = park.name
    }
  }
}
