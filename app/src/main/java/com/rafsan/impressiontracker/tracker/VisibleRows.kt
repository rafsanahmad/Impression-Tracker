package com.rafsan.impressiontracker.tracker

class VisibleRows(
    val firstCompletelyVisible: Int,
    val lastCompletelyVisible: Int
) {
    private var VISIBLE_PERCENTAGE = 90

    fun setVisiblePercentage(percentage: Int) {
        this.VISIBLE_PERCENTAGE = percentage
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as VisibleRows
        return if (firstCompletelyVisible != that.firstCompletelyVisible) false else lastCompletelyVisible == that.lastCompletelyVisible
    }

    override fun hashCode(): Int {
        var result = firstCompletelyVisible
        result = VISIBLE_PERCENTAGE * result + lastCompletelyVisible
        return result
    }

    override fun toString(): String {
        return "Visible Row{" +
                "first=" + firstCompletelyVisible +
                ", last=" + lastCompletelyVisible +
                '}'
    }
}