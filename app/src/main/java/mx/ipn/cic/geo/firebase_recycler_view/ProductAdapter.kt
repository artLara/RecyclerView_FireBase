package mx.ipn.cic.geo.firebase_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val producto: TextView = view.findViewById(R.id.textProducto)
    private val precio: TextView = view.findViewById(R.id.textPrecio)
    private val existencia: TextView = view.findViewById(R.id.textExistencia)

    fun bind(product: Product) {
        producto.text = product.producto
        precio.text = product.precio
        existencia.text = product.existencia
    }
}

class ProductAdapter: RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // TODO("Not yet implemented")
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // TODO("Not yet implemented")
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        // TODO("Not yet implemented")
        return products.size
    }

    var products = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
}