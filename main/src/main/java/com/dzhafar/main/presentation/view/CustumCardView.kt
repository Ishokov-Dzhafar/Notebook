package com.dzhafar.main.presentation.view

import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.dzhafar.core_db_api.px
import kotlin.math.min

class CustumCardView : ViewGroup {

    companion object {
        const val TAG = "CustomCardView"
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    constructor(context: Context) : super(context)
    var paintShadow: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    var paintStar: Paint = Paint().apply {
        color = Color.LTGRAY
        strokeWidth = 5.px * 1f
    }

    private val padding = 15.px

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        Log.d(TAG, "onMeasureMode width = $widthMode height = $heightMode")

        when (widthMode) {
            MeasureSpec.UNSPECIFIED -> {
                Log.d(TAG, "onMeasureMode UNSPECIFIED")
                Log.d(TAG, "onMeasureSize UNSPECIFIED widthSize = $widthSize, " +
                        "heightSize = $heightSize")
            }
            MeasureSpec.AT_MOST -> {
                Log.d(TAG, "onMeasureMode AT_MOST")
                Log.d(TAG, "onMeasureSize AT_MOST widthSize = $widthSize, " +
                        "heightSize = $heightSize")
                setMeasuredDimension(widthSize, heightSize)
            }
            MeasureSpec.EXACTLY -> {
                Log.d(TAG, "onMeasureMode EXACTLY")
                Log.d(TAG, "onMeasureSize EXACTLY widthSize = $widthSize, " +
                        "heightSize = $heightSize")
            }
        }

        if (childCount == 0) return

        var maxHeight = 0

        for (i in 0 until childCount) {
            Log.d(TAG, "MARGINS = ${getChildAt(i).marginTop}")
            val widthSpec = MeasureSpec.makeMeasureSpec(widthSize - 2 * padding, widthMode)
            val heightSpec = MeasureSpec.makeMeasureSpec(heightSize - 2 * padding, heightMode)
            measureChild(getChildAt(i), widthSpec, heightSpec)
            maxHeight += getChildAt(i).measuredHeight + getChildAt(i).marginTop +
                    getChildAt(i).marginBottom
        }

        maxHeight += 2 * padding
        Log.d(TAG, "maxHeight = $maxHeight")

        val mode = MeasureSpec.makeMeasureSpec(maxHeight, heightMode)
        val widthmode = MeasureSpec.makeMeasureSpec(getChildAt(0).measuredWidth +
                getChildAt(0).marginLeft + getChildAt(0).marginRight + padding,
            widthMode)

        setMeasuredDimension(widthmode, mode)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d(TAG, "onLayout $childCount")
        Log.d(TAG, "onLayout left = $left, right = $right, top = $top, bottom = $bottom")
        if (childCount == 0) return

        val leftPosition = padding
        var topPosition = padding
        for (i in 0 until childCount) {
            getChildAt(i).layout(leftPosition, topPosition, leftPosition +
                    getChildAt(i).measuredWidth, topPosition + getChildAt(i).measuredHeight)
            topPosition += getChildAt(i).measuredHeight
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        if (canvas == null) return
        Log.d(TAG, "dispatchDraw height = $height  width = $width")

        val radius = 5.px * 1f
        val dx = 1.px * 1f
        val dy = 1.px * 1f

        paintShadow.setShadowLayer(radius, dx, dy, Color.BLUE)

        canvas.drawRoundRect(RectF(radius, radius, (width - 2 * radius) * 1f,
            (height - 2 * radius) * 1f), radius, radius, paintShadow)

        var mid = width / 2.toFloat()
        val min = min(width, height).toFloat() - 2 * padding
        val fat = min / 17
        val half = min / 2
        val rad = half - fat
        mid -= half
        paintStar.strokeWidth = fat
        paintStar.style = Paint.Style.STROKE
        canvas.drawCircle(mid + half, half + padding, rad, paintStar)
        val path = Path()
        path.reset()
        paintStar.style = Paint.Style.FILL
        // top left
        path.moveTo(mid + half * 0.5f, half * 0.84f + padding)
        // top right
        // top right
        path.lineTo(mid + half * 1.5f, half * 0.84f + padding)
        // bottom left
        // bottom left
        path.lineTo(mid + half * 0.68f, half * 1.45f + padding)
        // top tip
        // top tip
        path.lineTo(mid + half * 1.0f, half * 0.5f + padding)
        // bottom right
        // bottom right
        path.lineTo(mid + half * 1.32f, half * 1.45f + padding)
        // top left
        // top left
        path.lineTo(mid + half * 0.5f, half * 0.84f + padding)

        path.close()
        canvas.drawPath(path, paintStar)
        super.dispatchDraw(canvas)
    }
}