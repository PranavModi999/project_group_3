package com.example.project_group_3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class ShopFragment : Fragment() {

    private var adapter: ProduceAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        val query = FirebaseDatabase.getInstance().reference.child("Produce")

        val options = FirebaseRecyclerOptions
            .Builder<Produce>()
            .setQuery(query, Produce::class.java)
            .build()

        adapter = ProduceAdapter(options)

        val rView: RecyclerView = view.findViewById(R.id.produceRecycler)
        rView.layoutManager = GridLayoutManager(context, 2)
        rView.adapter = adapter
        Log.i("test77", "inside product " + query)

        return view
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    private fun createDummyData() {
        val dummyData: Array<Produce> = arrayOf(
            Produce(
                1,
                "Apple",
                "FRUIT",
                1.0,
                "Fresh and juicy",
                0.5,
                "gs://produce-app-group-3.appspot.com/apple.jpg"
            ),
            Produce(
                2,
                "Carrot",
                "VEGETABLE",
                0.5,
                "Crunchy and nutritious",
                0.2,
                "gs://produce-app-group-3.appspot.com/carrot.jpg"
            ),
            Produce(
                3,
                "Banana",
                "FRUIT",
                0.8,
                "Sweet and potassium-rich",
                0.4,
                "gs://produce-app-group-3.appspot.com/banana.jpg"
            ),
            Produce(
                4,
                "Strawberry",
                "FRUIT",
                2.0,
                "Delicious and packed with antioxidants",
                0.3,
                "gs://produce-app-group-3.appspot.com/strawberry.jpg"
            ),
            Produce(
                5,
                "cabbage",
                "VEGETABLE",
                0.7,
                "Versatile and low in calories",
                0.4,
                "gs://produce-app-group-3.appspot.com/cabbage.jpg"
            )
        )
        val produceRef = FirebaseDatabase.getInstance().getReference("Produce")
        for (item in dummyData) {
            produceRef.child(item.id.toString()).setValue(item)
        }
    }
}