package com.example.volleyballonline

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MyActivity : AppCompatActivity() {

    private val mySQLHelper = MySQLHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity) // 关联对应的布局文件

        // 获取从 FragmentD 传递过来的 username
        val username = intent.getStringExtra("username")!!

        Toast.makeText(this@MyActivity, "获取数据中...", Toast.LENGTH_SHORT).show()


        // 返回按钮
            // 初始化 backImage
        val backImage: ImageView = findViewById(R.id.backImage)
            // 设置点击事件监听
        backImage.setOnClickListener {
            finish() // 结束当前 Activity，返回到之前的 Fragment
        }

        lifecycleScope.launch {
            try {
                var a1n:String? = null
                var a1t:String? = null
                var a1l:String? = null

                var a2n:String? = null
                var a2t:String? = null
                var a2l:String? = null

                var a3n:String? = null
                var a3t:String? = null
                var a3l:String? = null

                var a4n:String? = null
                var a4t:String? = null
                var a4l:String? = null

                // 在 IO 线程执行数据库操作
                val attend1 = withContext(Dispatchers.IO) {
                    mySQLHelper.findIfInSet(username,1)
                }
                val attend2 = withContext(Dispatchers.IO) {
                    mySQLHelper.findIfInSet(username, 2)
                }
                val attend3 = withContext(Dispatchers.IO) {
                    mySQLHelper.findIfInSet(username, 3)
                }
                val attend4 = withContext(Dispatchers.IO) {
                    mySQLHelper.findIfInSet(username, 5)
                }
                if (attend1) {
                    a1n = mySQLHelper.findActivityName(1)
                    a1l = mySQLHelper.findActivityLocation(1)
                    a1t = mySQLHelper.findActivityTime(1)
                }
                if (attend2) {
                    a2n = mySQLHelper.findActivityName(2)
                    a2l = mySQLHelper.findActivityLocation(2)
                    a2t = mySQLHelper.findActivityTime(2)
                }
                if (attend3) {
                    a3n = mySQLHelper.findActivityName(3)
                    a3l = mySQLHelper.findActivityLocation(3)
                    a3t = mySQLHelper.findActivityTime(3)
                }
                if (attend4) {
                    a4n = mySQLHelper.findActivityName(5)
                    a4l = mySQLHelper.findActivityLocation(5)
                    a4t = mySQLHelper.findActivityTime(5)
                }

                val values: List<String?> = listOfNotNull(
                    a1n, a1l, a1t,
                    a2n, a2l, a2t,
                    a3n, a3l, a3t,
                    a4n, a4l, a4t
                )

                // 提取非null值
                val nonNullValues = mutableListOf<String>()
                val nullValues = mutableListOf<String?>()

                // 手动过滤非null和null值
                for (value in values) {
                    if (value != null) {
                        nonNullValues.add(value)  // 将非null值添加到 nonNullValues
                    } else {
                        nullValues.add(value)  // 将null值添加到 nullValues
                    }
                }

                // 合并非null值和null值，确保非null值在前
                val result = nonNullValues + nullValues

                // 依次将值赋给中继变量 z1v1 到 z4v3
                val z1v1 = result.getOrNull(0)
                val z1v2 = result.getOrNull(1)
                val z1v3 = result.getOrNull(2)
                val z2v1 = result.getOrNull(3)
                val z2v2 = result.getOrNull(4)
                val z2v3 = result.getOrNull(5)
                val z3v1 = result.getOrNull(6)
                val z3v2 = result.getOrNull(7)
                val z3v3 = result.getOrNull(8)
                val z4v1 = result.getOrNull(9)
                val z4v2 = result.getOrNull(10)
                val z4v3 = result.getOrNull(11)

                // 获取 TextView 控件
                val a1v1: TextView = findViewById(R.id.activity1View1)
                val a1v2: TextView = findViewById(R.id.activity1View2)
                val a1v3: TextView = findViewById(R.id.activity1View3)

                val a2v1: TextView = findViewById(R.id.activity2View1)
                val a2v2: TextView = findViewById(R.id.activity2View2)
                val a2v3: TextView = findViewById(R.id.activity2View3)

                val a3v1: TextView = findViewById(R.id.activity3View1)
                val a3v2: TextView = findViewById(R.id.activity3View2)
                val a3v3: TextView = findViewById(R.id.activity3View3)

                val a4v1: TextView = findViewById(R.id.activity4View1)
                val a4v2: TextView = findViewById(R.id.activity4View2)
                val a4v3: TextView = findViewById(R.id.activity4View3)

                // 将 z1v1 到 z4v3 的值依次赋值给对应的 TextView
                a1v1.text = z1v1
                a1v2.text = z1v2
                a1v3.text = z1v3
                a2v1.text = z2v1
                a2v2.text = z2v2
                a2v3.text = z2v3
                a3v1.text = z3v1
                a3v2.text = z3v2
                a3v3.text = z3v3
                a4v1.text = z4v1
                a4v2.text = z4v2
                a4v3.text = z4v3

                // 设置隐藏逻辑
                val activity1Layout: ConstraintLayout = findViewById(R.id.activity1Layout)
                val activity2Layout: ConstraintLayout = findViewById(R.id.activity2Layout)
                val activity3Layout: ConstraintLayout = findViewById(R.id.activity3Layout)
                val activity4Layout: ConstraintLayout = findViewById(R.id.activity4Layout)


                if (!a1v1.text.isNullOrEmpty()) {
                    activity1Layout.visibility = View.VISIBLE
                } else {
                    activity1Layout.visibility = View.GONE
                }

                if (!a2v1.text.isNullOrEmpty()) {
                    activity2Layout.visibility = View.VISIBLE
                } else {
                    activity2Layout.visibility = View.GONE
                }

                if (!a3v1.text.isNullOrEmpty()) {
                    activity3Layout.visibility = View.VISIBLE
                } else {
                    activity3Layout.visibility = View.GONE
                }

                if (!a4v1.text.isNullOrEmpty()) {
                    activity4Layout.visibility = View.VISIBLE
                } else {
                    activity4Layout.visibility = View.GONE
                }



            }catch (e:Exception) {
                Log.e("MyActivity", "发生异常: ${e.message}", e)
                Toast.makeText(this@MyActivity,"发生错误: 用户数据连接失败", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
