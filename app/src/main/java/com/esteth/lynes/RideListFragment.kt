package com.esteth.lynes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


private const val ARG_PARK_ID = "parkId"

/**
 * A simple [Fragment] subclass.
 * Use the [RideListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
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
}
