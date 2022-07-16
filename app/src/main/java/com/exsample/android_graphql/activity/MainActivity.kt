package com.exsample.android_graphql.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.exception.ApolloException
import com.exsample.android_graphql.DeleteUserMutation
import com.exsample.android_graphql.InsertUserMutation
import com.exsample.android_graphql.UpdateUserMutation
import com.exsample.android_graphql.UserListQuery
import com.exsample.android_graphql.databinding.ActivityMainBinding
import com.exsample.android_graphql.network.GraphQL
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // getUserList()

        insertUser("name2", "rocket2", "twitter2")
    }

    private fun getUserList() {
        lifecycleScope.launch launchWhenResumed@{
            val response = try {
                GraphQL.get().query(UserListQuery(10)).execute()
            } catch (e: ApolloException) {
                Log.d("MainActivitydwjkdbkwfbw", e.toString())
                return@launchWhenResumed
            }
            val users = response.data?.users
            Log.d("MainActivitydwjkdbkwfbw", users.toString())
        }
    }

    private fun insertUser(name: String, rocket: String, twitter: String){
        lifecycleScope.launch launchWhenResumed@{
            val result = try {
                GraphQL.get().mutation(
                    InsertUserMutation(name, rocket, twitter)
                ).execute()
            }catch (e: Exception){
                Log.d("MainActivitydwjkdbkwfbw", e.toString())
                return@launchWhenResumed
            }
            Log.d("MainActivitydwjkdbkwfbw", result.toString())
        }
    }

//    private fun updateUser(id: String, name: String, rocket: String, twitter: String) {
//        lifecycleScope.launch launchWhenResumed@{
//            val result = try {
//                GraphQL.get().mutation(
//                    UpdateUserMutation(id, name, rocket, twitter)
//                ).execute()
//            } catch (e: ApolloException) {
//                Log.d("MainActivity", e.toString())
//                return@launchWhenResumed
//            }
//            Log.d("MainActivity", result.toString())
//        }
//    }

    private fun deleteUser(id: String) {
        lifecycleScope.launch launchWhenResumed@{
            val result = try {
                GraphQL.get().mutation(DeleteUserMutation(id)).execute()
            } catch (e: ApolloException) {
                Log.d("MainActivity", e.toString())
                return@launchWhenResumed
            }
            Log.d("MainActivity", result.toString())
        }
    }
}