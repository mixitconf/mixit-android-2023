package org.mixitconf.model.enums

import org.mixitconf.R

enum class TalkFormat(val duration: Int, val label: Int) {
    CLOSING_SESSION(25, R.string.talk_format_closing_session),
    KEYNOTE(25, R.string.talk_format_keynote),
    KEYNOTE_SURPRISE(25, R.string.talk_format_keynote_surprise),
    LIGHTNING_TALK(5, R.string.talk_format_random),
    RANDOM(25, R.string.talk_format_random),
    TALK(50, R.string.talk_format_talk),
    WORKSHOP(110, R.string.talk_format_workshop),
    INTERVIEW(25, R.string.talk_format_interview),
    ON_AIR(25, R.string.talk_format_interview),

    PLANNING_WELCOME(45, R.string.talk_format_planning_reception),
    PLANNING_INTRODUCTION_SESSION(15, R.string.talk_format_planning_introduction_session),
    PLANNING_INTRODUCTION_MIXETTE(15, R.string.talk_format_planning_introduction_mixette),
    PLANNING_PAUSE_10_MIN(10, R.string.talk_format_planning_pause_10),
    PLANNING_PAUSE_20_MIN(20, R.string.talk_format_planning_pause_20),
    PLANNING_PAUSE_25_MIN(25, R.string.talk_format_planning_pause_25),
    PLANNING_PAUSE_30_MIN(30, R.string.talk_format_planning_pause_30),
    PLANNING_PARTY(240, R.string.talk_format_planning_party),
    PLANNING_LUNCH(90, R.string.talk_format_planning_lunch),
    PLANNING_LUNCH2(35, R.string.talk_format_planning_lunch),
    PLANNING_ORGA_SPEECH(15, R.string.talk_format_planning_orha_speech),
    PLANNING_DAY(0, R.string.talk_format_planning_day);

    val isTalk: Boolean
        get() = listOf(TALK, WORKSHOP, KEYNOTE, RANDOM, KEYNOTE_SURPRISE, CLOSING_SESSION, LIGHTNING_TALK, INTERVIEW, ON_AIR)
            .contains(this)
}
