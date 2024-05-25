package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), OnItemClickListener {


    // private lateinit var btnImc: LinearLayout
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.label_imc,
                color = Color.GREEN
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.ic_baseline_visibility_24,
                textStringId = R.string.label_tmb,
                color = Color.YELLOW
            )
        )


        val adapter = MainAdapter(mainItems) { id ->
            when(id){
                1 -> {
                    val intent = Intent(this@MainActivity, ImcActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this@MainActivity, TmbActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this, 2)


//        btnImc = findViewById(R.id.btn_imc)
//
//        btnImc.setOnClickListener {
//            // Navegar para a próxima tela
//            val i = Intent(this, ImcActivity::class.java) // Criando a intenção
//            startActivity(i)
//        }
    }

    override fun onClick(id: Int) {

    }

    private inner class MainAdapter(private val mainItems: List<MainItem>, private val onItemClickListener: (Int) -> Unit ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
            val viewHolder = MainViewHolder(view)
            return viewHolder
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }

        override fun getItemCount(): Int {
            return mainItems.size
        }

        private inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){
            fun bind(item: MainItem){
                val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                val name: TextView = itemView.findViewById(R.id.item_txt_name)
                val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

                img.setImageResource(item.drawableId)
                name.setText(item.textStringId)
                container.setBackgroundColor(item.color)

                container.setOnClickListener {
                    onItemClickListener.invoke(item.id)

                }

            }
        }

    }




}