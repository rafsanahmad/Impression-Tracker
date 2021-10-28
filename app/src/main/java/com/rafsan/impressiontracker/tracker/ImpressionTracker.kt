package com.rafsan.impressiontracker.tracker

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class ImpressionTracker {

    private val TAG = ImpressionTracker::class.java.getName()
    private var THRESHOLD_MS = 500L
    private lateinit var publishSubject: Subject<VisibleRows>
    private var disposable: Disposable? = null
    private var onSuccess: Consumer<VisibleRows>? = null

    fun setTimeThreshold(threshold: Long) {
        this.THRESHOLD_MS = threshold
    }

    fun startTracking(
        onSuccess: Consumer<VisibleRows>
    ) {
        this.onSuccess = onSuccess
        publishSubject = PublishSubject.create()
        disposable = publishSubject
            .distinctUntilChanged()
            .throttleWithTimeout(THRESHOLD_MS, TimeUnit.MILLISECONDS)
            .subscribe(this::onCallback, this::onError)
    }

    fun postViewEvent(visibleRows: VisibleRows) {
        publishSubject.onNext(visibleRows)
    }

    fun stopTracking() {
        disposable?.dispose()
    }

    private fun onError(throwable: Throwable) {
        Log.d(TAG, "OnError in tracking: $throwable")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onCallback(visibleRows: VisibleRows) {
        onSuccess?.accept(visibleRows)
    }
}