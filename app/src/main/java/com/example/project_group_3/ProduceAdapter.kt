package com.example.project_group_3

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProduceAdapter(options: FirebaseRecyclerOptions<Produce>) :
    FirebaseRecyclerAdapter<Produce, ProduceAdapter.ProduceViewHolder>(options) {


    override fun onBindViewHolder(holder: ProduceViewHolder, position: Int, model: Produce) {
        holder.txtName.text = model.name
        holder.txtType.text = model.type.toString()
        holder.txtPrice.text = model.pricePerPound.toString()
        Log.i("test77", "onBindViewHolder: "+model.name)
        val storageRef: StorageReference =
            FirebaseStorage.getInstance().getReferenceFromUrl(model.image)
        Glide.with(holder.imgCover.context).load(storageRef).into(holder.imgCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduceAdapter.ProduceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.i("test77", "inside create" )

        return ProduceViewHolder(inflater, parent)
    }
    class ProduceViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.produce_grid_item, parent, false)) {
        val txtName: TextView = itemView.findViewById(R.id.itemName)
        val txtType: TextView = itemView.findViewById(R.id.itemType)
        val txtPrice: TextView = itemView.findViewById(R.id.itemType)
        val imgCover:ImageView=itemView.findViewById(R.id.itemImage)

    }
}