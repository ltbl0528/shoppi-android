package com.shoppi.app.ui.common

import androidx.lifecycle.Observer

//data가 한 번 소비되면 더 이상 사용되지 않도록 하는 것
class Event<T>(private val content: T) {
    //data 소비 여부
    private var hasBeenHandled = false

    //hasBeenHandled가 false일 경우에만 content 반환
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
            //livedata의 data가 변경됐음을 알림받을 때마다 데이터 소비를 확인한다
            //모든 livedata가 이 과정을 처리해야 함
        }
    }
}

/*
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnHandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnHandledContent(it)
        }
    }

}
