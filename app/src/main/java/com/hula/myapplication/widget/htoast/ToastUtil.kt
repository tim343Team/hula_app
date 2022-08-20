package com.hula.myapplication.widget.htoast

import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.ScreenUtils
import com.hula.myapplication.R
import com.hula.myapplication.app.ActivityLifecycleManager
import com.hula.myapplication.util.HUtilScreen
import com.hula.myapplication.util.HUtils
import java.lang.ref.WeakReference

object ToastUtil {

    private var mMarkDialogList = mutableListOf<Dialog>()
    private var mNormalToast: WeakReference<HToast>? = null
    private var maxWidth = 0


    private fun createToast(mark: Boolean, activity: Activity, attchParentWindow: Boolean): HToast {
        hideToast()
        val xToast: HToast = if (attchParentWindow) {
            HToastFactory.factory(activity)
        } else {
            HToastFactory.factory(activity.application)
        }
        mNormalToast = WeakReference(xToast)
        return xToast
    }


    private fun showXToast(text: String? = null, mark: Boolean, duration: Int?, iconView: View?) {

        val activity = ActivityLifecycleManager.get().curActivity() ?: return
        if (text.isNullOrEmpty() && iconView == null) {
            return
        }
        if (duration == 0) {
            return
        }
        val xToast: HToast = createToast(mark, activity, duration == null)
        val contentView: View = createView(activity,null, iconView, text)
        if (duration != null) {
            xToast.setDuration(duration)
        }
        xToast.setOutsideTouchable(!mark)
        xToast.setContentView(contentView)
        xToast.show()
    }

    private fun createView(
        activity: Activity,
        viewGroup: ViewGroup?,
        iconView: View?,
        text: String?
    ): View {
        val contentView: View = LayoutInflater.from(activity).inflate(R.layout.view_toast, viewGroup, false)
        if (iconView != null) {
            val iconLayout = contentView.findViewById<FrameLayout>(R.id.icon_layout)
            iconLayout.visibility = View.VISIBLE
            iconView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            iconLayout.addView(iconView)
            contentView.minimumHeight = contentView.minimumWidth
        }
        if (!text.isNullOrEmpty()) {
            val tvContent = contentView.findViewById<TextView>(R.id.tv_text)
            tvContent.visibility = View.VISIBLE
            tvContent.text = text
            if (maxWidth == 0) {
                maxWidth = ScreenUtils.getScreenWidth() - HUtilScreen.dp2px(tvContent.context, 48F)
            }
            tvContent.maxWidth = maxWidth
        }
        return contentView
    }

    @JvmStatic
    @JvmOverloads
    fun showLoading(content: String, activity: Activity? = null) {
        HUtils.runOnUi {
            val _activity = activity ?: ActivityLifecycleManager.get().curActivity() ?: return@runOnUi
            hideLoading()
            val iconView = LoadingView(_activity)
            val dialog = Dialog(_activity,R.style.LDialogStyle_LoadingStyle)
            val bgView = FrameLayout(_activity)
            val contentView = createView(_activity, bgView, iconView, content)
            val layoutParams = contentView.layoutParams as FrameLayout.LayoutParams
            layoutParams.gravity = Gravity.CENTER
            bgView.addView(contentView,layoutParams)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(true)
            dialog.setContentView(bgView,ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
            dialog.show()
            mMarkDialogList.add(dialog)
            dialog.setOnDismissListener {
                mMarkDialogList.remove(dialog)
            }
        }
    }

    @JvmStatic
    @JvmOverloads
    fun showIconToast(content: String, iconId: Int, mark: Boolean = false, duration: Int = 2000) {
        HUtils.runOnUi {
            val activity = ActivityLifecycleManager.get().curActivity() ?: return@runOnUi
            val iv = ImageView(activity)
            iv.setImageResource(iconId)
            showXToast(content, mark, duration, iv)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun showToast(content: String, mark: Boolean = false, duration: Int = 2000) {
        HUtils.runOnUi {
            showXToast(content, mark, duration, null)
        }
    }


    @JvmStatic
    @JvmOverloads
    fun showSuccessToast(content: String, mark: Boolean = false, duration: Int = 2000) {
        HUtils.runOnUi {
            showIconToast(content, R.mipmap.icon_success, mark, duration)
        }

    }

    @JvmStatic
    @JvmOverloads
    fun showFailToast(content: String, mark: Boolean = false, duration: Int = 2000) {
        HUtils.runOnUi {
            showIconToast(content, R.mipmap.icon_remind, mark, duration)
        }
    }

    @JvmStatic
    fun hideLoading() {
        HUtils.runOnUi {
            val iterator = mMarkDialogList.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                iterator.remove()
                kotlin.runCatching {
                    next.cancel()
                }
            }
        }

    }

    @JvmStatic
    fun hideToast() {
        HUtils.runOnUi {
            mNormalToast?.get()?.cancel()
            mNormalToast = null
        }
    }


}