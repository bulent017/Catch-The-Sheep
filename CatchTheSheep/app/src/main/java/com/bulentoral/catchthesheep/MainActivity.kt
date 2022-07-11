package com.bulentoral.catchthesheep

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bulentoral.catchthesheep.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score = 0
    private lateinit var scoreText:TextView
    private lateinit var timeText:TextView

    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        scoreText = binding.scoreText
        timeText = binding.timeText

        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        for (image in imageArray){
            image.visibility = View.INVISIBLE
        }

    }

    fun increaseScore(view: View)
    {
        score++
        scoreText.text = "Score: $score"
    }
    fun start(view: View){

        // CountDown Timer

        object : CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {
                timeText.text = "Time: ${p0/1000}"


            }

            override fun onFinish() {


                timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Total Score: $score")
                alert.setNeutralButton("Cancel"){
                        dialog:DialogInterface,which ->

                }
                alert.show()
                scoreText.text = "Score: "
            }

        }.start()
        hideImages()
    }


    fun hideImages(){

        runnable = object :Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE

                }
                val randomIndex =(0..8).random()

                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,200) // zorluk seviyesı
            }

        }
        handler.post(runnable)

    }
}