package com.hula.myapplication.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider

/**
 * 将子View 的 样式 扣一个 ShapeAppearanceModel 样式出来
 */
open class ShapeableFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleRes) {
    companion object {
        val lazyPathProvider by lazy { ShapeAppearancePathProvider() }
    }

    private val pathProvider = lazyPathProvider
    private val destination: RectF = RectF()
    private val maskRect: RectF = RectF()
    private var clearPaint: Paint = Paint()
    private val maskPath = Path()

    private val path = Path()

    private var shapeAppearanceModel: ShapeAppearanceModel =
        ShapeAppearanceModel.builder(context, attrs, defStyleRes, 0).build()

    init {
        clearPaint.isAntiAlias = true
        clearPaint.color = Color.WHITE
        clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    }

    fun setShapeAppearanceModel(mShapeAppearanceModel: ShapeAppearanceModel) {
        this.shapeAppearanceModel = mShapeAppearanceModel
        updateShapeMask(width,height)
        invalidate()
    }

    override fun onDetachedFromWindow() {
        setLayerType(LAYER_TYPE_NONE, null)
        super.onDetachedFromWindow()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateShapeMask(width, height)
    }

    private fun updateShapeMask(width: Int, height: Int) {
        destination[paddingLeft.toFloat(), paddingTop.toFloat(), (width - paddingRight).toFloat()] =
            (height - paddingBottom).toFloat()
        pathProvider.calculatePath(shapeAppearanceModel, 1f, destination, path)
        // Remove path from rect to draw with clear paint.
        maskPath.rewind()
        maskPath.addPath(path)
        // Do not include padding to clip the background too.
        maskRect[0f, 0f, width.toFloat()] = height.toFloat()
        maskPath.addRect(maskRect, Path.Direction.CCW)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        canvas.drawPath(maskPath, clearPaint)
    }
}