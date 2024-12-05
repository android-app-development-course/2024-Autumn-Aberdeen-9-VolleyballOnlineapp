package com.example.volleyballonline.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.volleyballonline.MySQLHelper
import com.example.volleyballonline.R
import android.widget.Toast
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.os.Looper
import android.os.Handler

class FragmentA : Fragment() {

    private val mySQLHelper = MySQLHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        // 初始化轮播图组件
        initSlider(view)

        val username = arguments?.getString("username")

        val textViewname11: TextView = view.findViewById(R.id.activity1View1)
        val textViewname12: TextView = view.findViewById(R.id.activity1View2)
        val textViewname13: TextView = view.findViewById(R.id.activity1View3)

        val textViewname21: TextView = view.findViewById(R.id.activity2View1)
        val textViewname22: TextView = view.findViewById(R.id.activity2View2)
        val textViewname23: TextView = view.findViewById(R.id.activity2View3)

        val textViewname31: TextView = view.findViewById(R.id.activity3View1)
        val textViewname32: TextView = view.findViewById(R.id.activity3View2)
        val textViewname33: TextView = view.findViewById(R.id.activity3View3)

        val textViewname41: TextView = view.findViewById(R.id.activity4View1)
        val textViewname42: TextView = view.findViewById(R.id.activity4View2)
        val textViewname43: TextView = view.findViewById(R.id.activity4View3)



        // button1 按钮模块
        val button1: Button = view.findViewById(R.id.button1)
        val noneButton1: Button = view.findViewById(R.id.nonebutton1)
        val alreadyButton1: Button = view.findViewById(R.id.alreadybutton1)
            // 按钮事件
        button1.setOnClickListener {
            val activityId = 1 // 活动 ID 为 1
            val usernameFromArgs = arguments?.getString("username")!!

            if (usernameFromArgs.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    Log.d("FragmentA", "尝试查找第一个空位...")

                    // 在 IO 线程执行数据库操作
                    val firstEmptySeat = withContext(Dispatchers.IO) {
                        mySQLHelper.findFirstEmptySeat(activityId)
                    }

                    if (firstEmptySeat != null) {
                        Log.d("FragmentA", "找到空位: $firstEmptySeat，准备插入用户名...")

                        // 插入用户名
                        val result = withContext(Dispatchers.IO) {
                            mySQLHelper.insertUsername(firstEmptySeat, usernameFromArgs, activityId)
                        }

                        if (result) {
                            Log.d("FragmentA", "用户名插入成功！")
                            Toast.makeText(requireContext(), "用户名插入成功", Toast.LENGTH_SHORT).show()
                            alreadyButton1.visibility = View.VISIBLE
                            button1.visibility = View.GONE
                            noneButton1.visibility = View.GONE
                        } else {
                            Log.e("FragmentA", "用户名插入失败，可能是SQL条件未满足。")
                            Toast.makeText(requireContext(), "插入失败，请稍后再试", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.w("FragmentA", "没有找到空位，插入失败。")
                        Toast.makeText(requireContext(), "没有空位可用", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("FragmentA", "发生异常: ${e.message}", e)
                    Toast.makeText(requireContext(), "发生错误: ${e.message}", Toast.LENGTH_SHORT).show()
                }



            }
        }

        // 第一次进入页面获取数据
        lifecycleScope.launch(Dispatchers.IO) {
            // 检查username是否在participant中
            val isInSet = mySQLHelper.findIfInSet(username!!, 1)

            // 检查是否有空位
            val hasEmptySeat = mySQLHelper.findTheSet(1)

            withContext(Dispatchers.Main) {
                if (isInSet) {
                    // 如果username已经在participant中，显示alreadyButton1
                    alreadyButton1.visibility = View.VISIBLE
                    button1.visibility = View.GONE
                    noneButton1.visibility = View.GONE
                } else if (hasEmptySeat) {
                    // 如果有空位，显示Button1
                    noneButton1.visibility = View.GONE
                    button1.visibility = View.VISIBLE
                    alreadyButton1.visibility = View.GONE
                } else {
                    // 如果没有空位，显示nonebutton1
                    button1.visibility = View.GONE
                    noneButton1.visibility = View.VISIBLE
                    alreadyButton1.visibility = View.GONE
                }
            }
        }
        // button1 按钮模块结束

        // button2 按钮模块
        val button2: Button = view.findViewById(R.id.button2)
        val noneButton2: Button = view.findViewById(R.id.nonebutton2)
        val alreadyButton2: Button = view.findViewById(R.id.alreadybutton2)
        // 按钮事件
        button2.setOnClickListener {
            val activityId = 2 // 活动 ID 为 2
            val usernameFromArgs = arguments?.getString("username")!!

            if (usernameFromArgs.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    Log.d("FragmentA", "尝试查找第一个空位...")

                    // 在 IO 线程执行数据库操作
                    val firstEmptySeat = withContext(Dispatchers.IO) {
                        mySQLHelper.findFirstEmptySeat(activityId)
                    }

                    if (firstEmptySeat != null) {
                        Log.d("FragmentA", "找到空位: $firstEmptySeat，准备插入用户名...")

                        // 插入用户名
                        val result = withContext(Dispatchers.IO) {
                            mySQLHelper.insertUsername(firstEmptySeat, usernameFromArgs, activityId)
                        }

                        if (result) {
                            Log.d("FragmentA", "用户名插入成功！")
                            Toast.makeText(requireContext(), "用户名插入成功", Toast.LENGTH_SHORT).show()
                            alreadyButton2.visibility = View.VISIBLE
                            button2.visibility = View.GONE
                            noneButton2.visibility = View.GONE
                        } else {
                            Log.e("FragmentA", "用户名插入失败，可能是SQL条件未满足。")
                            Toast.makeText(requireContext(), "插入失败，请稍后再试", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.w("FragmentA", "没有找到空位，插入失败。")
                        Toast.makeText(requireContext(), "没有空位可用", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("FragmentA", "发生异常: ${e.message}", e)
                    Toast.makeText(requireContext(), "发生错误: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 第一次进入页面获取数据
        lifecycleScope.launch(Dispatchers.IO) {
            // 检查username是否在participant中
            val isInSet = mySQLHelper.findIfInSet(username!!, 2)

            // 检查是否有空位
            val hasEmptySeat = mySQLHelper.findTheSet(2)

            withContext(Dispatchers.Main) {
                if (isInSet) {
                    // 如果username已经在participant中，显示alreadyButton2
                    alreadyButton2.visibility = View.VISIBLE
                    button2.visibility = View.GONE
                    noneButton2.visibility = View.GONE
                } else if (hasEmptySeat) {
                    // 如果有空位，显示Button2
                    noneButton2.visibility = View.GONE
                    button2.visibility = View.VISIBLE
                    alreadyButton2.visibility = View.GONE
                } else {
                    // 如果没有空位，显示nonebutton2
                    button2.visibility = View.GONE
                    noneButton2.visibility = View.VISIBLE
                    alreadyButton2.visibility = View.GONE
                }
            }
        }
        // button2 按钮模块结束

        // button3 按钮模块
        val button3: Button = view.findViewById(R.id.button3)
        val noneButton3: Button = view.findViewById(R.id.nonebutton3)
        val alreadyButton3: Button = view.findViewById(R.id.alreadybutton3)
        // 按钮事件
        button3.setOnClickListener {
            val activityId = 3 // 活动 ID 为 3
            val usernameFromArgs = arguments?.getString("username")!!

            if (usernameFromArgs.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    Log.d("FragmentA", "尝试查找第一个空位...")

                    // 在 IO 线程执行数据库操作
                    val firstEmptySeat = withContext(Dispatchers.IO) {
                        mySQLHelper.findFirstEmptySeat(activityId)
                    }

                    if (firstEmptySeat != null) {
                        Log.d("FragmentA", "找到空位: $firstEmptySeat，准备插入用户名...")

                        // 插入用户名
                        val result = withContext(Dispatchers.IO) {
                            mySQLHelper.insertUsername(firstEmptySeat, usernameFromArgs, activityId)
                        }

                        if (result) {
                            Log.d("FragmentA", "用户名插入成功！")
                            Toast.makeText(requireContext(), "用户名插入成功", Toast.LENGTH_SHORT).show()
                            alreadyButton3.visibility = View.VISIBLE
                            button3.visibility = View.GONE
                            noneButton3.visibility = View.GONE
                        } else {
                            Log.e("FragmentA", "用户名插入失败，可能是SQL条件未满足。")
                            Toast.makeText(requireContext(), "插入失败，请稍后再试", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.w("FragmentA", "没有找到空位，插入失败。")
                        Toast.makeText(requireContext(), "没有空位可用", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("FragmentA", "发生异常: ${e.message}", e)
                    Toast.makeText(requireContext(), "发生错误: ${e.message}", Toast.LENGTH_SHORT).show()
                }



            }
        }

        // 第一次进入页面获取数据
        lifecycleScope.launch(Dispatchers.IO) {
            // 检查username是否在participant中
            val isInSet = mySQLHelper.findIfInSet(username!!, 3)

            // 检查是否有空位
            val hasEmptySeat = mySQLHelper.findTheSet(3)

            withContext(Dispatchers.Main) {
                if (isInSet) {
                    // 如果username已经在participant中，显示alreadyButton3
                    alreadyButton3.visibility = View.VISIBLE
                    button3.visibility = View.GONE
                    noneButton3.visibility = View.GONE
                } else if (hasEmptySeat) {
                    // 如果有空位，显示Button3
                    noneButton3.visibility = View.GONE
                    button3.visibility = View.VISIBLE
                    alreadyButton3.visibility = View.GONE
                } else {
                    // 如果没有空位，显示nonebutton3
                    button3.visibility = View.GONE
                    noneButton3.visibility = View.VISIBLE
                    alreadyButton3.visibility = View.GONE
                }
            }
        }
        // button3 按钮模块结束

        // button4 按钮模块
        val button4: Button = view.findViewById(R.id.button4)
        val noneButton4: Button = view.findViewById(R.id.nonebutton4)
        val alreadyButton4: Button = view.findViewById(R.id.alreadybutton4)
        // 按钮事件
        button4.setOnClickListener {
            val activityId = 5 // 活动 ID 为 4
            val usernameFromArgs = arguments?.getString("username")!!

            if (usernameFromArgs.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    Log.d("FragmentA", "尝试查找第一个空位...")

                    // 在 IO 线程执行数据库操作
                    val firstEmptySeat = withContext(Dispatchers.IO) {
                        mySQLHelper.findFirstEmptySeat(activityId)
                    }

                    if (firstEmptySeat != null) {
                        Log.d("FragmentA", "找到空位: $firstEmptySeat，准备插入用户名...")

                        // 插入用户名
                        val result = withContext(Dispatchers.IO) {
                            mySQLHelper.insertUsername(firstEmptySeat, usernameFromArgs, activityId)
                        }

                        if (result) {
                            Log.d("FragmentA", "用户名插入成功！")
                            Toast.makeText(requireContext(), "报名成功", Toast.LENGTH_SHORT).show()
                            alreadyButton4.visibility = View.VISIBLE
                            button4.visibility = View.GONE
                            noneButton4.visibility = View.GONE
                        } else {
                            Log.e("FragmentA", "用户名插入失败，可能是SQL条件未满足。")
                            Toast.makeText(requireContext(), "插入失败，请稍后再试", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.w("FragmentA", "没有找到空位，插入失败。")
                        Toast.makeText(requireContext(), "没有空位可用", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("FragmentA", "发生异常: ${e.message}", e)
                    Toast.makeText(requireContext(), "发生错误: ${e.message}", Toast.LENGTH_SHORT).show()
                }



            }
        }

        // 第一次进入页面获取数据
        lifecycleScope.launch(Dispatchers.IO) {
            // 检查username是否在participant中
            val isInSet = mySQLHelper.findIfInSet(username!!, 5)

            // 检查是否有空位
            val hasEmptySeat = mySQLHelper.findTheSet(5)

            withContext(Dispatchers.Main) {
                if (isInSet) {
                    // 如果username已经在participant中，显示alreadyButton3
                    alreadyButton4.visibility = View.VISIBLE
                    button4.visibility = View.GONE
                    noneButton4.visibility = View.GONE
                } else if (hasEmptySeat) {
                    // 如果有空位，显示Button3
                    noneButton4.visibility = View.GONE
                    button4.visibility = View.VISIBLE
                    alreadyButton4.visibility = View.GONE
                } else {
                    // 如果没有空位，显示nonebutton3
                    button4.visibility = View.GONE
                    noneButton4.visibility = View.VISIBLE
                    alreadyButton4.visibility = View.GONE
                }
            }
        }
        // button3 按钮模块结束






        // 使用协程来调用挂起函数
        lifecycleScope.launch(Dispatchers.IO) {
            //第一个活动块
            val name1: String? = mySQLHelper.findActivityName(1)
            val location1: String? = mySQLHelper.findActivityLocation(1)
            val time1: String? = mySQLHelper.findActivityTime(1)

            val name2: String? = mySQLHelper.findActivityName(2)
            val location2: String? = mySQLHelper.findActivityLocation(2)
            val time2: String? = mySQLHelper.findActivityTime(2)

            val name3: String? = mySQLHelper.findActivityName(3)
            val location3: String? = mySQLHelper.findActivityLocation(3)
            val time3: String? = mySQLHelper.findActivityTime(3)

            val name4: String? = mySQLHelper.findActivityName(5)
            val location4: String? = mySQLHelper.findActivityLocation(5)
            val time4: String? = mySQLHelper.findActivityTime(5)

            withContext(Dispatchers.Main) {
                // 在主线程中更新UI
                textViewname11.text = name1
                textViewname12.text = location1
                textViewname13.text = time1

                textViewname21.text = name2
                textViewname22.text = location2
                textViewname23.text = time2

                textViewname31.text = name3
                textViewname32.text = location3
                textViewname33.text = time3

                textViewname41.text = name4
                textViewname42.text = location4
                textViewname43.text = time4
            }


        }




        return view


    }
    private fun initSlider(view: View) {
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerSlider)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayoutIndicator)

        // 图片
        val imageResources = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
        )

        // 适配器
        val sliderAdapter = SliderAdapter(imageResources)
        viewPager.adapter = sliderAdapter

        // 指示器
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // 自动滚动逻辑
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            viewPager.setCurrentItem((viewPager.currentItem + 1) % sliderAdapter.itemCount, true)
        }
        val delay = 2000L // 每2秒切换一次

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, delay)
            }
        })

        handler.postDelayed(runnable, delay)
    }

}
