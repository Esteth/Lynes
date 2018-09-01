package com.esteth.lynes.data

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.room.*

@Entity
data class Park(
    @ColumnInfo(name = "name") val name: String) {

  @PrimaryKey(autoGenerate = true)
  var id: Int = 0

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

  @Insert
  fun insert(park: Park)
}