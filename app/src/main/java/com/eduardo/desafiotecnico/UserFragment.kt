package com.eduardo.desafiotecnico


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduardo.desafiotecnico.adapter.UserAdapter
import com.eduardo.desafiotecnico.model.User
import com.eduardo.desafiotecnico.model.UserResponse
import com.eduardo.desafiotecnico.service.RetrofitService
import com.eduardo.desafiotecnico.service.UserApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : Fragment() {

    private lateinit var service: UserApi
    private var usersList: ArrayList<User> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        service = RetrofitService.getUserService()

        setUpRecyclerView(usersList)
        getUser()

        return inflater.inflate(R.layout.fragment_user, container, false)

    }

    private fun getUser() {
        val res = service.getUsers()
        res.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (!response.isSuccessful) {
                    Log.e("API REQUEST", response.message())
                } else {
                    val response = response.body()

                    if (response != null) {
                        for (user: User in response.users) {
                            usersList.add(user)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("API REQUEST", "Verifique a internet. Erro -> ${t.message}")
            }
        })

    }

    private fun setUpRecyclerView(users: List<User>) {
        userRecyclerView.layoutManager = LinearLayoutManager(activity)
        userRecyclerView.adapter = UserAdapter(users)
    }

}
