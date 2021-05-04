package com.grsu.schedule_project.presentation.common.listadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grsu.schedule_project.common.utils.DATE_API_RESPONSE_PATTERN
import com.grsu.schedule_project.common.utils.DATE_SCHEDULE_VIEW_PATTERN
import com.grsu.schedule_project.common.utils.DateUtils
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.vo.LessonTeacherVo
import com.grsu.schedule_project.data.model.vo.SubGroupVo
import com.grsu.schedule_project.databinding.DayDateBinding
import com.grsu.schedule_project.databinding.DayLessonBinding
import com.grsu.schedule_project.presentation.common.utils.SpanDataObject
import com.grsu.schedule_project.presentation.common.utils.setClickableSpan
import com.grsu.schedule_project.presentation.common.utils.setColoredSpan
import com.grsu.schedule_project.presentation.common.utils.setUnderlinedSpan
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ScheduleAdapter :
    ListAdapter<ScheduleListItem, RecyclerView.ViewHolder>(ScheduleDiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return currentList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_SCHEDULE_DATE -> {
                val binding = DayDateBinding.inflate(layoutInflater, parent, false)
                DateViewHolder(binding)
            }
            TYPE_SCHEDULE_LESSON -> {
                val binding = DayLessonBinding.inflate(layoutInflater, parent, false)
                LessonViewHolder(binding)
            }
            else -> throw RuntimeException("No such viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_SCHEDULE_DATE ->
                (holder as DateViewHolder).bind(getItem(position) as DateListItem)
            TYPE_SCHEDULE_LESSON ->
                (holder as LessonViewHolder).bind(getItem(position) as LessonListItem)
        }
    }

    class DateViewHolder(private val binding: DayDateBinding) :
        RecyclerView.ViewHolder(binding.root), KoinComponent {

        private val utils: Utils by inject()
        private val dateUtils: DateUtils by inject()

        fun bind(dateListItem: DateListItem) {
            val responseDateFormat = dateUtils.getDateFormatInstance(DATE_API_RESPONSE_PATTERN)
            val viewDateFormat = dateUtils.getDateFormatInstance(DATE_SCHEDULE_VIEW_PATTERN)

            val dayOfWeek =
                dateListItem.dayOfWeekNum?.toIntOrNull()?.let { dateUtils.getDayOfWeek(it - 1) }
            val shortDate = dateListItem.date?.let {
                val dateInMillis = responseDateFormat.parse(it)
                if (dateInMillis != null) viewDateFormat.format(dateInMillis) else null
            }
            val dateResult = "$dayOfWeek, $shortDate"

            binding.titleSingleLine.text = dateResult
        }
    }
}

class LessonViewHolder(private val binding: DayLessonBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(lessonListItem: LessonListItem) {
        binding.timeStart.text = lessonListItem.timeStart
        binding.timeEnd.text = lessonListItem.timeEnd
        binding.type.text = lessonListItem.lessonType
        binding.title.text = lessonListItem.title
        binding.subGroup.text = lessonListItem.subGroup?.title
        binding.teacher.text = lessonListItem.teacher?.fullname
        binding.address.text = lessonListItem.address

        lessonListItem.clickableSpan?.let {
            binding.teacher.setClickableSpan(it)
        }
        lessonListItem.coloredSpan?.let {
            binding.teacher.setColoredSpan(it)
        }
        lessonListItem.underlinedSpan?.let {
            binding.teacher.setUnderlinedSpan(it)
        }
    }
}

const val TYPE_SCHEDULE_DATE = 0
const val TYPE_SCHEDULE_LESSON = 1

abstract class ScheduleListItem(
    open val localId: String,
    open val type: Int
) {

    override fun equals(other: Any?): Boolean = super.equals(other)

    override fun hashCode(): Int {
        var result = localId.hashCode()
        result = 31 * result + type
        return result
    }
}

data class DateListItem(
    override val localId: String,
    val dayOfWeekNum: String?,
    val date: String?,
    override val type: Int = TYPE_SCHEDULE_DATE
) : ScheduleListItem(localId, type)

data class LessonListItem(
    override val localId: String,
    val timeStart: String? = null,
    val timeEnd: String? = null,
    val teacher: LessonTeacherVo? = null,
    val label: String? = null,
    val lessonType: String? = null,
    val title: String? = null,
    val address: String? = null,
    val room: String? = null,
    val subGroup: SubGroupVo? = null,
    val clickableSpan: SpanDataObject? = null,
    val coloredSpan: SpanDataObject? = null,
    val underlinedSpan: SpanDataObject? = null,
    override val type: Int = TYPE_SCHEDULE_LESSON
) : ScheduleListItem(localId, type)

object ScheduleDiffCallback : DiffUtil.ItemCallback<ScheduleListItem>() {

    override fun areItemsTheSame(
        oldItem: ScheduleListItem,
        newItem: ScheduleListItem
    ): Boolean {
        return oldItem.localId == newItem.localId
    }

    override fun areContentsTheSame(
        oldItem: ScheduleListItem,
        newItem: ScheduleListItem
    ): Boolean {
        return oldItem == newItem
    }
}