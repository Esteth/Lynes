package com.esteth.lynes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


private const val ARG_RIDE_ID = "rideId"

/**
 * A simple [Fragment] subclass.
 * Use the [RideFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RideFragment : Fragment() {
  private var rideId: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      rideId = it.getString(ARG_RIDE_ID)
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_ride, container, false)
  }
}
