package com.dzhafar.main.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewDebug.ExportedProperty
import android.view.ViewDebug.IntToString
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.dzhafar.core_db_api.px
import com.dzhafar.main.R
import kotlin.math.max
import kotlin.math.min

class CustomCardView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, 0)

    var paintShadow: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    var paintStar: Paint = Paint().apply {
        color = Color.LTGRAY
        strokeWidth = 5.px.toFloat()
    }

    private var paddingInView = 16.px
    private var paddingOutView = 16.px

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView)

        try {
            paddingInView = typedArray.getDimensionPixelSize(
                R.styleable.CustomCardView_paddingInView, 16.px
            )
            paddingOutView = typedArray.getDimensionPixelSize(
                R.styleable.CustomCardView_paddingOutView, 16.px
            )
        } finally {
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        when (parentWidthMode) {
            MeasureSpec.UNSPECIFIED -> {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
            MeasureSpec.AT_MOST -> {
                setMeasuredDimension(widthSize, heightSize)
            }
            MeasureSpec.EXACTLY -> {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
        }

        if (childCount == 0) return

        measureChildren(widthSize, parentWidthMode, parentHeightMode)

        val heightMode = MeasureSpec.makeMeasureSpec(calculateMaxHeight(), parentHeightMode)
        val widthMode = MeasureSpec.makeMeasureSpec(calculateMaxWidth(), parentWidthMode)
        setMeasuredDimension(widthMode, heightMode)
    }

    private fun calculateMaxHeight(): Int {
        var maxHeight = 2 * paddingInView + 2 * paddingOutView
        for (i in 0 until childCount) {
            maxHeight += getChildAt(i).measuredHeight
        }
        return maxHeight
    }

    private fun calculateMaxWidth(): Int {
        var maxWidth = 0
        for (i in 0 until childCount) {
            val currentWidth = getChildAt(0).measuredWidth +
                    getChildAt(0).marginLeft + getChildAt(0).marginRight +
                    paddingInView
            maxWidth = max(currentWidth, maxWidth)
        }
        return maxWidth
    }

    private fun measureChildren(widthSize: Int, parentWidthMode: Int, parentHeightMode: Int) {
        for (i in 0 until childCount) {
            val widthSpec =
                MeasureSpec.makeMeasureSpec(
                    widthSize - 2 * (paddingInView + paddingOutView),
                    parentWidthMode
                )
            val heightSpec =
                MeasureSpec.makeMeasureSpec(0, parentHeightMode)

            measureChild(getChildAt(i), widthSpec, heightSpec)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (childCount == 0) return

        val leftPosition = paddingInView
        var topPosition = paddingInView
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val layoutParams = childView.layoutParams as CustomLayoutParams
            when (layoutParams.gravity) {
                -1 -> {
                    childView.layout(
                        leftPosition + paddingInView,
                        topPosition + childView.marginTop + paddingOutView,
                        right - leftPosition - paddingInView,
                        topPosition + childView.measuredHeight + paddingOutView +
                                childView.marginBottom
                    )
                }
                Gravity.START -> {
                    childView.layout(
                        leftPosition + paddingInView,
                        topPosition + childView.marginTop + paddingOutView,
                        leftPosition + paddingInView + childView.measuredWidth,
                        topPosition + childView.measuredHeight + paddingOutView +
                                childView.marginBottom
                    )
                }
                Gravity.END -> {
                    childView.layout(
                        max(leftPosition + paddingInView, right - paddingInView -
                                paddingOutView - childView.measuredWidth),
                        topPosition + childView.marginTop + paddingOutView,
                        right - paddingInView - paddingOutView,
                        topPosition + childView.measuredHeight + paddingOutView +
                                childView.marginBottom
                    )
                }
            }

            topPosition += childView.measuredHeight
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        if (canvas == null) return

        val radius = 5.px.toFloat()
        val dx = 1.px.toFloat()
        val dy = 1.px.toFloat()

        paintShadow.setShadowLayer(radius, dx, dy, Color.BLUE)

        canvas.drawRoundRect(
            RectF(
                radius + paddingOutView,
                radius + paddingOutView,
                (width - 2 * radius) - paddingOutView,
                (height - 2 * radius) - paddingOutView
            ), radius, radius, paintShadow
        )

        var mid = width / 2.toFloat()
        val min = min(width, height).toFloat() - 2 * paddingInView - 2 * paddingOutView
        val fat = min / 17
        val half = min / 2
        val rad = half - fat
        mid -= half
        paintStar.strokeWidth = fat
        paintStar.style = Paint.Style.STROKE
        canvas.drawCircle(mid + half, half + paddingInView + paddingOutView, rad,
            paintStar)
        val path = Path()
        path.reset()
        paintStar.style = Paint.Style.FILL
        // top left
        path.moveTo(mid + half * 0.5f, half * 0.84f + paddingInView + paddingOutView)
        // top right
        // top right
        path.lineTo(mid + half * 1.5f, half * 0.84f + paddingInView + paddingOutView)
        // bottom left
        // bottom left
        path.lineTo(mid + half * 0.68f, half * 1.45f + paddingInView + paddingOutView)
        // top tip
        // top tip
        path.lineTo(mid + half * 1.0f, half * 0.5f + paddingInView + paddingOutView)
        // bottom right
        // bottom right
        path.lineTo(mid + half * 1.32f, half * 1.45f + paddingInView + paddingOutView)
        // top left
        // top left
        path.lineTo(mid + half * 0.5f, half * 0.84f + paddingInView + paddingOutView)

        path.close()
        canvas.drawPath(path, paintStar)
        super.dispatchDraw(canvas)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): CustomLayoutParams? {
        return CustomLayoutParams(context, attr = attrs)
    }

    class CustomLayoutParams : ViewGroup.LayoutParams {
        @ExportedProperty(
            category = "layout",
            mapping = [
                IntToString(from = -1, to = "NONE"),
                IntToString(from = Gravity.NO_GRAVITY, to = "NONE"),
                IntToString(from = Gravity.START, to = "START"),
                IntToString(from = Gravity.END, to = "END"),
                IntToString(from = Gravity.CENTER_HORIZONTAL, to = "CENTER_HORIZONTAL")
            ]
        )
        var gravity = -1

        constructor(width: Int, height: Int) : super(width, height)

        constructor(context: Context, attr: AttributeSet?) : super(context, attr) {
            if (attr == null) return
            val typedArray = context.obtainStyledAttributes(attr, R.styleable.CustomCardView_Layout)
            try {
                gravity = typedArray.getInt(
                    R.styleable.CustomCardView_Layout_android_layout_gravity, -1
                )
            } finally {
                typedArray.recycle()
            }
        }
    }
}
