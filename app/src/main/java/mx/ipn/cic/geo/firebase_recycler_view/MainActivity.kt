package mx.ipn.cic.geo.firebase_recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productList: RecyclerView = findViewById(R.id.recyclerProducts)
        val productAdapter = ProductAdapter()
        productList.adapter = productAdapter
        val data = ArrayList<Product>()

        //Conexion a FireBase
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val referenciaBD : DatabaseReference = database.getReference("app_recyclerview/productos")

        referenciaBD.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {
                val product = dataSnapshot.getValue(Product::class.java)
                if (product != null) {
                    if(product.id > -1 && product.id<data.size){
                        data[product.id] = product
                        productAdapter.products = data
                    }
                }
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val product = dataSnapshot.getValue(Product::class.java)
                if (product != null) {
                    data.add(product)
                    productAdapter.products = data
                }
            }
        })
    }
}