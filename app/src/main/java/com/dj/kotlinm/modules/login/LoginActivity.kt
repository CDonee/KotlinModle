package com.dj.kotlinm.modules.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dj.kotlinm.MainActivity
import com.dj.kotlinm.api.DJWanAndroidApi
import com.dj.kotlinm.config.Constans
import com.dj.kotlinm.entity.LoginResponse
import com.dj.kotlinm.net.APIResponse
import com.dj.kotlinm.net.ApiManager
import com.example.kotlinm.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_login.*


/**
 * @author djc
 *
 * @time 2023/5/18/018  23:12
 *
 * @desc for login
 *
 **/
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        // WanAndroidAPI.class  ---  WanAndroidAPI::class.java
        // val api: WanAndroidAPI = APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)

        user_login_bt.setOnClickListener(onCLickLister)
    }

    private val onCLickLister = View.OnClickListener { view ->
        // id = 内部根据= 知道你是 要 setId，  否则就是getId
        when(view.id) {
            // 登录
            R.id.user_login_bt -> {

                val userNameStr = user_phone_et.text.toString()
                val userPwdStr = user_password_et.text.toString()
                Log.d(Constans.TAG, "userName:$userNameStr,  userPwd:$userPwdStr")

                ApiManager.createDjWanAndroidApi(DJWanAndroidApi::class.java)

                    // 全部都是RxJava知识了
                    .login(userNameStr, userPwdStr)  // 起点  往下流  ”包装Bean“
                    .subscribeOn(Schedulers.io()) // 给上面请求服务器的操作，分配异步线程
                    .observeOn(AndroidSchedulers.mainThread()) // 给下面更新UI操作，分配main线程

                    /*.subscribe(object : Consumer<LoginResponseWrapper<LoginResponse>> {
                            override fun accept(t: LoginResponseWrapper<LoginResponse>?) {
                                // 我这里是更新UI，拿到了包装Bean，实际上我不需要包装Bean
                            }

                        })*/

                    .subscribe(object: APIResponse<LoginResponse>(this)
                    {
                        override fun success(data: LoginResponse ?) {
                            // 成功  data UI
                            Log.e(Constans.TAG, "success: $data")
                            Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()
//                            Intent(this@LoginActivity,MainActivity::class.java).apply {
//                                startActivity(this)
//                            }
//                            this@LoginActivity.finish()
                        }

                        override fun failure(errorMsg: String?) {
                            // 失败 msg UI
                            Log.e(Constans.TAG, "failure: errorMsg:$errorMsg")
                            Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
                        }

                    })
            }


        }
    }

}