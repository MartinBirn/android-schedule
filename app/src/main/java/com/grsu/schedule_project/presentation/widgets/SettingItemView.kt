package com.grsu.schedule_project.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.grsu.schedule_project.R
import com.grsu.schedule_project.databinding.ViewSettingItemBinding

class SettingItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {
    private var layoutInflater = LayoutInflater.from(context)
    private val binding = ViewSettingItemBinding.inflate(layoutInflater, this)

    private var title = ""
        set(value) {
            field = value
            binding.title.text = value
        }

    private var description: String? = null
        set(value) {
            field = value
            binding.description.text = value
        }

    private var iconRes = 0
        set(value) {
            field = value
            binding.icon.setImageDrawable(context.getDrawable(value))
        }

    init {
        if (attrs != null) {
            val array = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SettingItemView,
                defStyleAttrs,
                0
            )
            title = array.getString(R.styleable.SettingItemView_title) ?: ""
            description = array.getString(R.styleable.SettingItemView_description)
            iconRes = array.getResourceId(R.styleable.SettingItemView_iconRes, 0)
        }
    }
}