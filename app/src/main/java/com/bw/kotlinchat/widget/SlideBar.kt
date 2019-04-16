package com.bw.kotlinchat.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bw.kotlinchat.R
import org.jetbrains.anko.sp
import org.jetbrains.anko.toast

/**
 * Create by Rgx on 2019/4/15 16:07
 * Description:
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
    var onMoveSlideLister:OnMoveSlideLister? = null
    var sectionHeight = 0f;
    val paint = Paint();
    companion object {
        private val SECTIONS = arrayOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

    }
    var textbaseline = 0f;
    init {
        paint.apply {
            color = Color.BLACK
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        sectionHeight = h * 1.0f/ SECTIONS.size;
        val metrics = paint.fontMetrics
        val textHeight = metrics.descent - metrics.ascent;
        textbaseline = sectionHeight / 2 + (textHeight / 2 - metrics.descent)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var x = width * 1.0f / 2;
        var y = sectionHeight;

        SECTIONS.forEach {
            canvas.drawText(it,x,y,paint)
            y += sectionHeight
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                setBackgroundColor(R.drawable.bg_slide_bar)
                var index = getTouchIndex(event);
                onMoveSlideLister?.getSlideIndex(SECTIONS[index])
            }
            MotionEvent.ACTION_MOVE ->{
                setBackgroundColor(R.drawable.bg_slide_bar)
                var index = getTouchIndex(event);
                onMoveSlideLister?.getSlideIndex(SECTIONS[index])
            }



            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                onMoveSlideLister?.onSlideFinish()
            }
        }



        return true
    }

    private fun getTouchIndex(event:MotionEvent): Int {
        var index = (event.y / sectionHeight).toInt()

        if(index < 0){
            index = 0
        }else if (index >= SECTIONS.size){
            index = SECTIONS.size - 1
        }

        return index;

    }


    interface OnMoveSlideLister {
        fun getSlideIndex(firstLetter:String)
        fun onSlideFinish()
    }
}