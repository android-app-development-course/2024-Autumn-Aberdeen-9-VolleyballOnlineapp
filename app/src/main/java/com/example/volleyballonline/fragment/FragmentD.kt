package com.example.volleyballonline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.volleyballonline.R
import com.example.volleyballonline.SharedViewModel
import android.content.Intent
import android.widget.Button
import com.example.volleyballonline.MyActivity
import android.util.Log
import android.widget.Toast


class FragmentD : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        Log.d("FragmentD", "onCreateView called")

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_d, container, false)

        val helloText: TextView = view.findViewById(R.id.welcomeView)

        // 从Bundle中获取username
        val username = arguments?.getString("username")

        helloText.text = "Hello, $username!" // 使用username更新TextView

        // 初始化按钮
        val activityButton: Button = view.findViewById(R.id.activityButton)

        // 设置点击事件监听
        activityButton.setOnClickListener {
            val intent = Intent(requireContext(), MyActivity::class.java)
            // 将 username 放入 Intent 中
            val username = arguments?.getString("username") ?: "default_username" // 确保 username 不为空
            intent.putExtra("username", username)
            startActivity(intent)
        }

        return view


    }
}