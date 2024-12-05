package com.example.volleyballonline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.volleyballonline.R
import android.content.Intent
import android.net.Uri
import com.example.volleyballonline.MySQLHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentB : Fragment() {

    private val mySQLHelper = MySQLHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_b, container, false)


        // 扣球模块
        val spikeVideo1: ImageView = view.findViewById(R.id.spike1)
        val spikeVideo2: ImageView = view.findViewById(R.id.spike2)
        val spikeVideo3: ImageView = view.findViewById(R.id.spike3)

        spikeVideo1.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(1,"video1")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        spikeVideo2.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(1,"video2")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        spikeVideo3.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(1,"video3")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        // 扣球模块结束


        // 发球模块
        val faVideo1: ImageView = view.findViewById(R.id.fa1)
        val faVideo2: ImageView = view.findViewById(R.id.fa2)
        val faVideo3: ImageView = view.findViewById(R.id.fa3)

        faVideo1.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(3,"video1")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        faVideo2.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(3,"video2")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        faVideo3.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(3,"video3")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        // 发球模块结束


        // 垫球模块
        val dianVideo1: ImageView = view.findViewById(R.id.dian1)
        val dianVideo2: ImageView = view.findViewById(R.id.dian2)
        val dianVideo3: ImageView = view.findViewById(R.id.dian3)

        dianVideo1.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(3,"video1")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        dianVideo2.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(3,"video2")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        dianVideo3.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(3,"video3")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        // 垫球模块结束


        // 传球模块
        val chuanVideo1: ImageView = view.findViewById(R.id.chuan1)
        val chuanVideo2: ImageView = view.findViewById(R.id.chuan2)
        val chuanVideo3: ImageView = view.findViewById(R.id.chuan3)

        chuanVideo1.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(4,"video1")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        chuanVideo2.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(4,"video2")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        chuanVideo3.setOnClickListener{
            // 使用协程从数据库获取 URL
            CoroutineScope(Dispatchers.Main).launch {
                val url = withContext(Dispatchers.IO) {
                    mySQLHelper.getURL(4,"video3")
                }
                if (url != null && url.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: Exception) {
                        println("无法打开浏览器")
                    }
                } else {
                    println("连接网络失败")
                }
            }
        }
        // 传球模块结束


        return view
    }
}