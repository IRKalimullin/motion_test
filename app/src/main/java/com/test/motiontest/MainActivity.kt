package com.test.motiontest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

class MainActivity() : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.vector_container)


        imageView.setImageResource(R.drawable.ic_vector)
        //imageView.setOnTouchListener(this)

        val relativeLayout = findViewById<RelativeLayout>(R.id.view_container)

        val imageParams = imageView.layoutParams
        val view = DrawLine(this)
        view.layoutParams = imageParams

        relativeLayout.addView(view)

    }

/*
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        var x : Float
        var y : Float

        var startX : Float
        var startY : Float

        var endX : Float
        var endY : Float

        when (p1?.action){

            MotionEvent.ACTION_MOVE -> {
                x = p1.x
                y = p1.y
            }

            MotionEvent.ACTION_DOWN -> {
                startX = p1.x
                startY = p1.y
            }

            MotionEvent.ACTION_UP -> {
                endX = p1.x
                endY = p1.y
            }
        }
        return true
    }*/

    //Дополнительный класс для рисования линии на экране
    class DrawLine(context: Context) : View(context) {

        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        var path = Path()

        init {
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 6F
            paint.color = Color.WHITE
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            var x : Float
            var y : Float

            var startX : Float
            var startY : Float

            var endX : Float
            var endY : Float

            when (event?.action){

                MotionEvent.ACTION_MOVE -> {
                    x = event.x
                    y = event.y

                    path.lineTo(x,y)
                }

                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y

                    path.moveTo(startX,startY)
                }

                MotionEvent.ACTION_UP -> {
                    endX = event.x
                    endY = event.y

                    path.lineTo(endX,endY)
                }
            }
            invalidate()
            return true
        }

        override fun onDraw(canvas: Canvas?) {
            canvas?.drawPath(path,paint)
        }
    }
}