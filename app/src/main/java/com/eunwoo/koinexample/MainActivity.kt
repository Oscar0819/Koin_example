package com.eunwoo.koinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eunwoo.koinexample.`object`.Student
import com.eunwoo.koinexample.`object`.Teacher
import com.eunwoo.koinexample.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // container 시작
        startKoin {
            androidContext(this@MainActivity)
            modules(
                    // module 시작
                    module {
                        // 하나만 생성되고 재활용되는 singleton object 만들기
                        single { Teacher("teacher-sophia") }
                        // 매번 새로 생성되는 Factory Object 만들기
                        factory { Student("student-oscar") }
//                        single(named("cho")) { Student("student-cho")}
//                        single(named("kim")) { Student("student-kim") }
                    }
                    // module 종료
            )
        }        // container 종료

        val teacher: Teacher by inject()
        val student: Student by inject()

        binding.textViewTeacher.text = teacher.name
        binding.textViewStudent.text = student.name

    }
}