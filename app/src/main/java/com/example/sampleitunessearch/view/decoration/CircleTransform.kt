package com.example.sampleitunessearch.view.decoration

import android.graphics.*
import com.squareup.picasso.Transformation

class CircleTransform : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }
        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(
            squaredBitmap,
            Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )
        paint.setShader(shader)
        paint.setAntiAlias(true)
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val r = size / 2f
        canvas.drawRoundRect(RectF(rect),10f,10f, paint)
        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}