package com.example.visitbosteri.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visitbosteri.R
import com.example.visitbosteri.adapters.AllHotelsAdapter
import com.example.visitbosteri.adapters.CatAdapter
import com.example.visitbosteri.models.AllHotels
import com.example.visitbosteri.models.Categories
import com.example.visitbosteri.models.Hotels
import com.example.visitbosteri.services.ReqService
import com.example.visitbosteri.services.RetInstance
import com.example.visitbosteri.ui.CategoryActivity
import com.example.visitbosteri.ui.DetailInfoActivity
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Response

class HomeFragment : Fragment(), CatAdapter.OnCatClickListener, AllHotelsAdapter.OnHotelClickListener {

    private var list: MutableList<AllHotels> = ArrayList()
    private  lateinit var retrofitService : ReqService

    private lateinit var homeViewModel: HomeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        catRecyclerInit()
        HotelRecyclerInit()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)



        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun catRecyclerInit(){
        val catList = generateCatList()

        catRecyclerview.adapter = CatAdapter(catList, this)
        catRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        catRecyclerview.setHasFixedSize(true)

    }

    private fun HotelRecyclerInit(){

      //  val AllAdList = generateList()

     //   allRecyclerview.adapter = AllHotelsAdapter(AllAdList, this)
        allRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        allRecyclerview.setHasFixedSize(true)

        retrofitService = RetInstance
            .getRetrofitInstance()
            .create(ReqService::class.java)

        val responseLiveData: LiveData<Response<Hotels>> = liveData {
            val response = retrofitService.getPost()
            emit(response)
        }
        responseLiveData.observe(viewLifecycleOwner, Observer {
            val postList= it.body()?.listIterator()
            if (postList!=null){
                while (postList.hasNext()){
                    val postsItem = postList.next()
                    list.add(AllHotels(postsItem.photo_main, postsItem.title, postsItem.address, postsItem.price, postsItem.category))

                    Log.e("AllHotelsTag", "Hotels $list " )
                    allRecyclerview.adapter?.notifyDataSetChanged()
                }
            }
        })

        allRecyclerview.adapter = AllHotelsAdapter(list, this)

    }

    private fun generateCatList() : List<Categories>{

        val list = ArrayList<Categories>()
        list.add(Categories(R.drawable.ic_done, "Rixos"))
        list.add(Categories(R.drawable.ic_done, "Vogue"))
        list.add(Categories(R.drawable.ic_done, "Royal"))
        list.add(Categories(R.drawable.ic_done, "Golden beach"))
        list.add(Categories(R.drawable.ic_done, "Уй"))
        list.add(Categories(R.drawable.ic_done, "Some hotel"))
        list.add(Categories(R.drawable.ic_done, "Bla bla hotel"))
        list.add(Categories(R.drawable.ic_done, "Lorem hotel"))

        return list
    }

//    private fun generateList() : List<AllHotels>{
//
//            val list = ArrayList<AllHotels>()
//        list.add(AllHotels(R.drawable.hotel, "Rixos", 74, "last update: 10.10.2020"))
//        list.add(AllHotels(R.drawable.hotel, "Vogue", 25, "last update: 11.10.2020"))
//        list.add(AllHotels(R.drawable.hotel, "Royal", 20, "last update: 6.10.2020"))
//        list.add(AllHotels(R.drawable.hotel, "Golden beach", 555, "last update: 4.10.2020"))
//        list.add(AllHotels(R.drawable.hotel, "Уй", 122, "last update: 10.10.2020"))
//        list.add(AllHotels(R.drawable.hotel, "Some hotel", 555, "last update: 11.10.2020"))
//        list.add(AllHotels(R.drawable.hotel, "Bla bla hotel", 787, "last update: 6.10.2020"))
//        list.add(AllHotels(R.drawable.hotel, "Lorem hotel", 888, "last update: 4.10.2020"))
//
//        return list
//    }

    override fun onCatClick(position: Int) {
        startActivity(Intent(context, CategoryActivity::class.java))
    }

    override fun onHotelClick(position: Int) {
        startActivity(Intent(context, DetailInfoActivity::class.java))
    }
}