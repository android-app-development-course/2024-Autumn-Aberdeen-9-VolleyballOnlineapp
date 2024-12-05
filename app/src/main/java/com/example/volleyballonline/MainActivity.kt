package com.example.volleyballonline

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.volleyballonline.fragment.FragmentA
import com.example.volleyballonline.fragment.FragmentB
import com.example.volleyballonline.fragment.FragmentD
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.AAA -> selectedFragment(0)
                R.id.BBB -> selectedFragment(1)
                // 如果有更多菜单项，继续添加else if
                else -> selectedFragment(3)
            }
            true
        }

        // 从Intent中获取username
        val username = intent.getStringExtra("username")
        // 默认显示FragmentA
        selectedFragment(0)
    }

    fun selectedFragment(position: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        hideFragment(fragmentTransaction)

        // 根据position显示不同的Fragment
        when (position) {
            0 -> {
                if (fragmenta == null) {
                    fragmenta = FragmentA()

                    val args = Bundle()
                    args.putString("username", intent.getStringExtra("username")) // 传递username
                    fragmenta!!.arguments = args

                    fragmentTransaction.add(R.id.content, fragmenta!!)
                } else {
                    fragmentTransaction.show(fragmenta!!)
                }
            }
            1 -> {
                if (fragmentb == null) {
                    fragmentb = FragmentB()
                    fragmentTransaction.add(R.id.content, fragmentb!!)
                } else {
                    fragmentTransaction.show(fragmentb!!)
                }
            }
            3 -> {
                if (fragmentd == null) {
                    fragmentd = FragmentD()

                    // 将username作为Bundle传递给FragmentD
                    val args = Bundle()
                    args.putString("username", intent.getStringExtra("username"))
                    fragmentd!!.arguments = args


                    fragmentTransaction.add(R.id.content, fragmentd!!)
                } else {
                    fragmentTransaction.show(fragmentd!!)
                }
            }
        }
        fragmentTransaction.commit()
    }

    fun hideFragment(fragmentTransaction: FragmentTransaction) {
        fragmenta?.let { fragmentTransaction.hide(it) }
        fragmentb?.let { fragmentTransaction.hide(it) }
        fragmentd?.let { fragmentTransaction.hide(it) }
    }

    companion object {
        private var fragmenta: FragmentA? = null
        private var fragmentb: FragmentB? = null
        private var fragmentd: FragmentD? = null
    }
}