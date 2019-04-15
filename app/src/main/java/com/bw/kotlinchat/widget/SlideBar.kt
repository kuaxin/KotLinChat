package com.bw.kotlinchat.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.sp

/**
 * Create by Rgx on 2019/4/15 16:07
 * Description:
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

    var sectionHeight = 0f;
    val paint = Paint();
    companion object {
        private val SECTIONS = arrayOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

    }

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
        val fl = sectionHeight / 2 - (textHeight / 2 - metrics.descent)

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



}