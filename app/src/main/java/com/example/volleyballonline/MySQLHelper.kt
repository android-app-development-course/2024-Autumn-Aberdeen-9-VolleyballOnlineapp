package com.example.volleyballonline

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import android.util.Log

class MySQLHelper {
    private val dbUrl = "jdbc:mysql://rm-wz94570b9nkwq41h49o.mysql.rds.aliyuncs.com:3306/volleyballonline"
    private val username = "VolleyballOnline"
    private val password = "!zfy200312261"

    suspend fun establishConnection(): Connection = withContext(Dispatchers.IO) {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection(dbUrl, username, password)
    }

    // 注册信息写入数据库
    suspend fun insertUser(username: String, password: String, age: String, phone_number: String) {
        withContext(Dispatchers.IO) {
            val connection = establishConnection()
            val sql = "INSERT INTO users (username, password, age, phone_number) VALUES (?, ?, ?, ?)"  //建表语句，向users表内插入用户名和密码

            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, username)
                preparedStatement.setString(2, password)
                preparedStatement.setString(3, age)
                preparedStatement.setString(4, phone_number)
                preparedStatement.executeUpdate()
            } finally {
                connection.close()
            }
        }
    }

    //登陆信息数据库中验证
    suspend fun checkLogin(username: String, password: String): Boolean = withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "SELECT * FROM users WHERE username=? AND password=?" //查询语句
        var result = false

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, username)
            preparedStatement.setString(2, password)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()
            result = resultSet.next()
        } finally {
            connection.close()
        }
        result
    }

    // 根据username查询用户id
    suspend fun getUserIdByUsername(username: String): Int? {
        val connection = establishConnection()
        val sql = "SELECT id FROM users WHERE username=?" // 查询语句
        var userId: Int? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, username)
            val resultSet: ResultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                userId = resultSet.getInt("id") // 获取查询到的用户id
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            connection.close()
        }

        return userId
    }

    suspend fun getUsernameById(userId: Int): String? {
        val connection = establishConnection()
        val sql = "SELECT username FROM users WHERE id=?" // 查询语句
        var username: String? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, userId)
            val resultSet: ResultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                username = resultSet.getString("username") // 获取查询到的用户名
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            connection.close()
        }

        return username
    }


    // 以下三个方法是对于fragmentA中的活动名，活动地点，活动时间的获取
    // 对fragmentA中的活动名
    suspend fun findActivityName(activity_id: Int): String? = withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "SELECT activity_name FROM activity WHERE activity_id=?"
        var activity_name: String? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()

            // 检查是否有结果并提取用户名
            if (resultSet.next()) {
                activity_name = resultSet.getString("activity_name")
            }
        } catch (e:SQLException) {
            e.printStackTrace()
            Log.e("MySQLHelper", "SQL Error: ${e.message}", e)
            return@withContext "获取活动名错误"
        } finally {
            connection.close()
        }

        activity_name
    }
    // 对fragmentA中的活动地点
    suspend fun findActivityLocation(activity_id: Int): String? = withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "SELECT activity_location FROM activity WHERE activity_id=?"
        var activity_location: String? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()

            // 检查是否有结果并提取用户名
            if (resultSet.next()) {
                activity_location = resultSet.getString("activity_location")
            }
        } catch (e:SQLException) {
            e.printStackTrace()
            return@withContext "获取活动地点错误"
        } finally {
            connection.close()
        }

        activity_location
    }
    // 对fragmentA中的活动时间
    suspend fun findActivityTime(activity_id: Int): String? = withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "SELECT activity_time FROM activity WHERE activity_id=?"
        var activity_time: String? = null

        try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            // 执行查询
            val resultSet: ResultSet = preparedStatement.executeQuery()

            // 检查是否有结果并提取用户名
            if (resultSet.next()) {
                activity_time = resultSet.getString("activity_time")
            }
        } catch (e:SQLException) {
            e.printStackTrace()
            return@withContext "获取活动时间错误"
        } finally {
            connection.close()
        }

        activity_time
    }

    suspend fun findTheSet(activity_id: Int): Boolean {
        val connection = establishConnection()
        // 定义一个列表来存储所有参与者的查询语句
        val participantFields = listOf(
            "participant_1", "participant_2", "participant_3", "participant_4",
            "participant_5", "participant_6", "participant_7", "participant_8",
            "participant_9", "participant_10", "participant_11", "participant_12"
        )
        // 遍历所有参与者字段
        for (field in participantFields) {
            val sql = "SELECT $field FROM activity WHERE activity_id=?"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            val resultSet = preparedStatement.executeQuery()
            // 检查是否有结果，如果没有结果则表示有空位
            if (!resultSet.next() || resultSet.getString(field).isNullOrEmpty()) {
                // 找到空位，返回true
                return true
            }
        }
        // 如果所有席位都已满，返回false
        return false
    }

    // 查看当前用户是否在活动中
    suspend fun findIfInSet(username: String, activity_id: Int): Boolean {
        val connection = establishConnection()
        val participantFields = listOf(
            "participant_1", "participant_2", "participant_3", "participant_4",
            "participant_5", "participant_6", "participant_7", "participant_8",
            "participant_9", "participant_10", "participant_11", "participant_12"
        )

        // 遍历所有参与者字段
        for (field in participantFields) {
            val sql = "SELECT $field FROM activity WHERE activity_id=? AND $field=?"
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, activity_id)
            preparedStatement.setString(2, username)
            val resultSet = preparedStatement.executeQuery()

            // 检查是否有结果，如果有结果则表示用户名在参与者列表中
            if (resultSet.next()) {
                return true
            }
        }

        connection.close() // 关闭数据库连接
        return false // 如果所有字段都检查完毕，没有找到用户名，则返回false
    }

    // 找到一个空位
    suspend fun findFirstEmptySeat(activityId: Int): String? {
        val connection = establishConnection()

        // 定义参与者字段列表
        val participantFields = listOf(
            "participant_1", "participant_2", "participant_3", "participant_4",
            "participant_5", "participant_6", "participant_7", "participant_8",
            "participant_9", "participant_10", "participant_11", "participant_12"
        )

        try {
            // 构建动态 SQL 查询语句
            val sql = buildString {
                append("SELECT ")
                append(participantFields.joinToString(", ") { "($it IS NULL OR $it = '') AS $it" })
                append(" FROM activity WHERE activity_id = ? LIMIT 1")
            }

            connection.prepareStatement(sql).use { preparedStatement ->
                preparedStatement.setInt(1, activityId)
                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        // 遍历结果集列，返回第一个为空的字段名
                        for (field in participantFields) {
                            if (resultSet.getBoolean(field)) {
                                Log.d("MySQLHelper", "找到空位字段: $field")
                                return field
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("MySQLHelper", "查找空位时发生异常: ${e.message}", e)
            e.printStackTrace() // 捕获异常
        } finally {
            connection.close() // 关闭连接
        }

        // 如果没有找到空值，返回 null
        Log.d("MySQLHelper", "没有找到空位。")
        return null
    }

    suspend fun insertUsername(theFirstEmptySet: String, username: String, activity_id: Int): Boolean {
        // 建立数据库连接
        val connection = establishConnection()
        val setField = theFirstEmptySet
        val userName = username
        val actId = activity_id
        val sql = "UPDATE activity SET $setField=? WHERE activity_id=? AND $setField IS NULL OR $setField=''"

        try {
            Log.d("MySQLHelper", "尝试插入用户名: $username 到字段: $theFirstEmptySet")
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, userName)
            preparedStatement.setInt(2, actId)

            // 执行更新
            val rowsAffected = preparedStatement.executeUpdate()
            Log.d("MySQLHelper", "更新影响行数: $rowsAffected")

            // 检查是否有行被更新
            return rowsAffected > 0
        } catch (e: SQLException) {
            Log.e("MySQLHelper", "插入用户名时发生异常: ${e.message}", e)
            e.printStackTrace()
            return false
        } finally {
            connection.close()  // 确保数据库连接被关闭
        }
    }

    suspend fun getURL(id: Int, videoMember: String): String? {

        val validColumns = setOf("video1", "video2", "video3") // 允许的列名集合

        if (videoMember !in validColumns) {
            throw IllegalArgumentException("Invalid column name: $videoMember")
        }

        val connection = establishConnection()

        val sql = "SELECT $videoMember FROM `videos` where `actionKind` = ?"
        var value:String? = null

        try {
            val preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setInt(1, id)

            val resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                value = resultSet.getString(videoMember)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            connection.close()
        }

        return value
    }



}





































