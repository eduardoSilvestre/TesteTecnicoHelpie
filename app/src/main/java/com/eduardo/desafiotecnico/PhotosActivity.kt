package com.eduardo.desafiotecnico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduardo.desafiotecnico.adapter.PhotosAdapter
import com.eduardo.desafiotecnico.model.PhotoResponse
import com.eduardo.desafiotecnico.model.Photos
import com.eduardo.desafiotecnico.service.PhotosApi
import com.eduardo.desafiotecnico.service.RetrofitService
import kotlinx.android.synthetic.main.activity_photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosActivity : AppCompatActivity() {

    private lateinit var service: PhotosApi
    private var photosList:ArrayList<Photos> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        service = RetrofitService.getPhotosService()
        getPhotos()
        setUpRecyclerView()
    }

    private fun getPhotos() {
        val res = service.getPhotos()
        res.enqueue(object : Callback<PhotoResponse>{
            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
                if (!response.isSuccessful){
                    Log.e("API REQUEST", response.message())
                }
                else{
                    val response = response.body()

                    if (response != null){
                        for (photo: Photos in response.photos){
                            photosList.add(photo)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                Log.e("API REQUEST", "Verifique a internet. Erro -> " + t.message)
            }
        })
    }

    fun setUpRecyclerView(){
        photosRecyclerView.layoutManager = LinearLayoutManager(this)
        photosRecyclerView.adapter =PhotosAdapter(this, photosList)
    }
}
