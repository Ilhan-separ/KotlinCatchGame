package com.example.kotlincatchgame

import android.app.Dialog
import android.content.DialogInterface
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kotlincatchgame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var countDownTimer: CountDownTimer
    var handler = Handler()
    var runnable = Runnable {  }

    var score =0;
    var imageArray = ArrayList<ImageView>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createArray()

        hideImage()

        countDownTimer = object : CountDownTimer(20500, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timeTextView.text = "Time : ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.timeTextView.text = "Time : 0"

                handler.removeCallbacks(runnable)

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("GG!")
                alert.setMessage("Your ${binding.scoreTextView.text},\nWanna Play Again?")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)

                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                    finishAffinity()
                })
                alert.show()
            }
        }.start()



    }


    fun hideImage(){

        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray)
                    image.visibility = View.INVISIBLE

                var random = Random()
                var randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,400)
            }
        }

        handler.post(runnable)
    }



    fun createArray(){
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
    }


    fun hitScore(view : View){
        score++
        binding.scoreTextView.text = "Score: $score"

    }


}