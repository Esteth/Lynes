package com.esteth.lynes.data

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(foreignKeys = [
  ForeignKey(
      entity = Park::class,
      parentColumns = ["id"],
      childColumns = ["parkId"],
      onDelete = CASCADE)]
)
data class Ride(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "parkId") val park: Park) {

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0

  object DiffCallback : DiffUtil.ItemCallback<Ride>() {
    override fun areItemsTheSame(oldItem: Ride, newItem: Ride): Boolean {
      return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Ride, newItem: Ride): Boolean {
      return oldItem.name == newItem.name
    }

  }
}

@Dao
interface RideDao {
  @Query("SELECT * FROM ride ORDER BY name ASC")
  fun rides(): LiveData<List<Ride>>

  @Query("SELECT * FROM ride WHERE parkId=:parkId ORDER BY name ASC")
  fun ridesInPark(parkId: Int): LiveData<List<Ride>>

  @Insert
  fun insert(ride: Ride)
}