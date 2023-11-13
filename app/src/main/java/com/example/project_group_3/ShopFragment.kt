package com.example.project_group_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        adapter = ProduceAdapter(options) { item ->
            Log.i("test2", "onCreateView:here " + item.name)
            val i: Intent = Intent(activity, DetailActivity::class.java)
            i.putExtra("SELECTED_ITEM", item)
            startActivity(i)
        }

        val rView: RecyclerView = view.findViewById(R.id.produceRecycler)
        rView.layoutManager = GridLayoutManager(context, 2)
        rView.adapter = adapter
        Log.i("test77", "inside product " + query)

//        createDummyData()
        return view
    }

    private fun clickListener(item: Produce) {
        Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    private fun createDummyData() {
        val dummyData: Array<Produce> = arrayOf(
            Produce(
                1,
                "Carrot",
                "VEGETABLE",
                0.5,
                "Crunchy and nutritious",
                1.0,
                "gs://produce-app-group-3.appspot.com/carrot.jpg"
            ),
            Produce(
                2,
                "Banana",
                "FRUIT",
                0.8,
                "Sweet and potassium-rich",
                1.0,
                "gs://produce-app-group-3.appspot.com/banana.jpg"
            ),
            Produce(
                3,
                "Strawberry",
                "FRUIT",
                2.0,
                "Delicious and packed with antioxidants",
                1.0,
                "gs://produce-app-group-3.appspot.com/strawberry.jpg"
            ),
            Produce(
                4,
                "Cabbage",
                "VEGETABLE",
                0.7,
                "Versatile and low in calories",
                1.0,
                "gs://produce-app-group-3.appspot.com/cabbage.jpg"
            ),
            Produce(
                5,
                "Green Apple",
                "FRUIT",
                1.0,
                "Enjoy the crisp and tangy delight of green apples, a refreshing choice for a healthy and flavorful snack.",
                1.0,
                "gs://produce-app-group-3.appspot.com/greenapple.jpeg"
            ),
            Produce(
                6,
                "Kiwi Fruits",
                "FRUIT",
                1.2,
                "Discover the invigorating taste of kiwi, a vibrant and nutrient-rich fruit that adds a zesty twist to your day.",
                1.0,
                "gs://produce-app-group-3.appspot.com/kiwi.jpeg"
            ),
            Produce(
                7,
                "Mango Fruits",
                "FRUIT",
                1.5,
                "Indulge in the tropical perfection of mangoes, nature's golden gift that brings a luscious burst of sweetness to your plate.",
                1.0,
                "gs://produce-app-group-3.appspot.com/mangoes.jpeg"
            ),
            Produce(
                8,
                "Dragon Fruits",
                "FRUIT",
                2.5,
                "Experience the exotic allure of dragon fruit, a vibrant and nutritious tropical delicacy that will elevate your culinary adventures.",
                1.0,
                "gs://produce-app-group-3.appspot.com/dragonfruit.jpeg"
            ),
            Produce(
                9,
                "Watermelon",
                "FRUIT",
                1.2,
                "Quench your thirst with the refreshing sweetness of watermelon, a juicy summertime essential for pure enjoyment.",
                1.0,
                "gs://produce-app-group-3.appspot.com/watermelon.jpeg"
            ),
            Produce(
                10,
                "Plums",
                "FRUIT",
                1.8,
                "Savor the succulent juiciness of plums, nature's sweet gems that add a burst of delightful flavor to your palate.",
                1.0,
                "gs://produce-app-group-3.appspot.com/plums.jpeg"
            ),
            Produce(
                11,
                "Eggplant",
                "VEGETABLE",
                1.2,
                "Indulge in the velvety richness of eggplant, a culinary delight for creating savory dishes with a touch of elegance.",
                1.0,
                "gs://produce-app-group-3.appspot.com/eggplant.jpeg"
            ),
            Produce(
                12,
                "Potato",
                "VEGETABLE",
                0.9,
                "Embrace the hearty goodness of potatoes, a versatile staple that brings comfort and flavor to your kitchen creations.",
                1.0,
                "gs://produce-app-group-3.appspot.com/potato.jpeg"
            ),
            Produce(
                13,
                "Beans",
                "VEGETABLE",
                0.6,
                "Experience the garden-fresh crunch and vibrant taste of green beans, a delicious addition to your meals.",
                1.0,
                "gs://produce-app-group-3.appspot.com/beans.jpeg"
            ),
            Produce(
                14,
                "Pumpkins",
                "VEGETABLE",
                1.0,
                "Discover the rich and earthy flavor of our green pumpkins, a unique ingredient to elevate your seasonal recipes.",
                1.0,
                "gs://produce-app-group-3.appspot.com/pumkins.jpeg"
            ),
            Produce(
                15,
                "Brussels Frozen",
                "FROZEN",
                2.3,
                "Savor the convenience and health benefits of our frozen Brussels sprouts, a nutritious side dish that's ready whenever you are.",
                1.0,
                "gs://produce-app-group-3.appspot.com/brussels.jpeg"
            ),
            Produce(
                16,
                "Corn Frozen",
                "FROZEN",
                1.5,
                "Enjoy the golden sweetness of our frozen corn, a versatile addition to your kitchen that brings a taste of summer all year round.",
                1.0,
                "gs://produce-app-group-3.appspot.com/corn.jpeg"
            ),
            Produce(
                17,
                "Peas Frozen",
                "FROZEN",
                1.0,
                "Lock in the garden-fresh goodness with our frozen peas, a convenient way to add vibrant color and nutrition to your meals.",
                1.0,
                "gs://produce-app-group-3.appspot.com/peas.jpeg"
            ),
            Produce(
                18,
                "Fish Frozen",
                "FROZEN",
                5.0,
                "Experience gourmet dining at home with our frozen sea bass fish, a culinary delight that brings the ocean's finest to your plate.",
                1.0,
                "gs://produce-app-group-3.appspot.com/fish.jpeg"
            ),
            Produce(
                19,
                "Almonds",
                "DRY FRUITS",
                12.0,
                "Discover the natural goodness of almonds, a crunchy and nutritious snack that fuels your day with wholesome energy.",
                1.0,
                "gs://produce-app-group-3.appspot.com/almonds.jpeg"
            ),
            Produce(
                20,
                "Cashew",
                "DRY FRUITS",
                10.0,
                "Indulge in the buttery richness of cashews, a delectable and versatile nut that adds flavor and nutrition to your routines.",
                1.0,
                "gs://produce-app-group-3.appspot.com/cashews.jpeg"
            ),
            Produce(
                21,
                "Walnuts",
                "DRY FRUITS",
                15.0,
                "Experience the earthy delight of walnuts, a nutrient-packed nut that elevates both taste and health in your everyday choices.",
                1.0,
                "gs://produce-app-group-3.appspot.com/walnuts.jpeg"
            ),
            Produce(
                22,
                "Raisins",
                "DRY FRUITS",
                8.0,
                "Savor the natural sweetness of raisins, a wholesome and convenient snack that adds a burst of flavor to your day.",
                1.0,
                "gs://produce-app-group-3.appspot.com/raisins.jpeg"
            ),
            Produce(
                23,
                "Figs",
                "DRY FRUITS",
                20.0,
                "Indulge in the succulent sweetness of figs, a luxurious and nutrient-rich fruit that delights the senses.",
                1.0,
                "gs://produce-app-group-3.appspot.com/figs.jpeg"
            ),
            Produce(
                24,
                "Pistachio",
                "DRY FRUITS",
                18.0,
                "Delight in the vibrant crunch of pistachios, a satisfying and nutritious snack that adds a touch of elegance to your routine.",
                1.0,
                "gs://produce-app-group-3.appspot.com/pistachio.jpeg"
            )
        )
        val produceRef = FirebaseDatabase.getInstance().getReference("Produce")
        for (item in dummyData) {
            produceRef.child(item.id.toString()).setValue(item)
        }
    }
}