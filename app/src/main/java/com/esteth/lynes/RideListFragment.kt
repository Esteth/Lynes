package com.esteth.lynes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_ride_list.*


private const val ARG_PARK_ID = "parkId"

/**
 * Fragment to display a list of rides within a single park.
 */
class RideListFragment : Fragment() {
  private var parkId: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      parkId = it.getString(ARG_PARK_ID)
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_ride_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    ridesRecycler
        .addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
  }
}
