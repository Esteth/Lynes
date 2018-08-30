package com.esteth.lynes.data

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.room.*

@Entity
data class Park(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String) {

  object DiffCallback : DiffUtil.ItemCallback<Park>() {
    override fun areItemsTheSame(oldItem: Park, newItem: Park): Boolean {
      return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Park, newItem: Park): Boolean {
      return oldItem.name == newItem.name
    }

  }
}

@Dao
interface ParkDao {
  @Query("SELECT * FROM park ORDER BY name ASC")
  fun parks(): LiveData<List<Park>>
}