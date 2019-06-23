package com.odaroid.budgee.utilities

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


/**
 * Created By David Odari
 * On 23/06/19
 *
 * Custom Line Divider for recycler view
 **/
class ListDividerDecoration(@NonNull private val context: Context, @DrawableRes resId: Int = -1) :
    RecyclerView.ItemDecoration() {

    private var divider: Drawable? = null

    init {
        if (resId == -1) {
            val attr = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
            divider = attr.getDrawable(0)
            attr.recycle()
        } else divider = ContextCompat.getDrawable(context, resId)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until (childCount)) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight

            divider!!.setBounds(left, top, right, bottom)
            divider!!.draw(c)
        }
    }
}
