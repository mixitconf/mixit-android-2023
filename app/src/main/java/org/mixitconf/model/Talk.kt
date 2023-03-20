package org.mixitconf.model

import android.content.res.Resources
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.github.rjeschke.txtmark.Processor
import org.mixitconf.*
import org.mixitconf.model.enums.Language
import org.mixitconf.model.enums.Room
import org.mixitconf.model.enums.TalkFormat
import org.mixitconf.model.enums.Topic
import java.util.*

@Entity
data class Talk(
    val remoteId: String,
    val format: TalkFormat,
    val event: String,
    val title: String,
    val summary: String,
    val speakerIds: String,
    val language: Language = Language.FRENCH,
    val description: String?,
    val topic: Topic,
    val room: Room,
    val start: Date,
    val end: Date,
    val favorite: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
) {

    // This list is only populated when we want to see the talk detail. For that we read speakers by their ids
    @Ignore
    val speakers: MutableList<Speaker> = mutableListOf()

    val speakerIdList
        get() = speakerIds.split(",")

    val topicDrawableResource
        get() = topic.drawableResourse

    val startTime: Date
        get() = start.toFrenchTime

    val endTime: Date
        get() = end.toFrenchTime

    val descriptionInMarkdown
        get() = if (description.isNullOrEmpty()) null else Processor.process(description.trim()).toHtml

    val summaryInMarkdown
        get() = if (summary.isEmpty()) "" else Processor.process(summary.trim()).toHtml

    /**
     * If time is elapsed we return a disabled color
     */
    fun getBackgroundColor(color: Int): Int =
        if (Date().time > endTime.time) R.color.unknown else color

    fun getTimeLabel(resources: Resources): String = String.format(
        resources.getString(R.string.talk_time_range),
        start.formatToString(),
        startTime.formatTimeToString(),
        endTime.formatTimeToString()
    )

    fun roomName(resources: Resources): String = room.let {
        resources.getString(R.string.talk_room_detail, resources.getString(it.i18nId), it.capacity.toString())
    }
}