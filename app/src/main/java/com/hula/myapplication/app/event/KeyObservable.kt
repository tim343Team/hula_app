package com.hula.myapplication.app.event

import java.lang.RuntimeException
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class KeyObservable : Observable() {
    private val observerToKeyObserver = ConcurrentHashMap<Observer, KeyObserver>()
    private val stickData = ConcurrentHashMap<String, Any>()
    fun sendData(key: String, value: Any) {
        setChanged()
        notifyObservers(arrayOf(key, value))
    }

    fun sendStickData(key: String, value: Any) {
        stickData[key] = value
        sendData(key, value)
    }

    fun removeStickData(key: String){
        stickData.remove(key)
    }

    override fun deleteObserver(o: Observer?) {
        var realO = o
        if (realO != null) {
            val _temp = observerToKeyObserver.remove(realO)
            if (_temp != null) {
                realO = _temp
            }
        }
        super.deleteObserver(realO)
    }

    override fun deleteObservers() {
        observerToKeyObserver.clear()
        super.deleteObservers()
    }

    override fun addObserver(o: Observer) {
        throw RuntimeException("can not call it")
    }

    fun addObserver(key: String, o: Observer) {
        val keyObserver = KeyObserver(key, o)
        observerToKeyObserver[o] = keyObserver
        super.addObserver(keyObserver)
        if (stickData.containsKey(key)) {
            val data = stickData[key]
            keyObserver.update(this, arrayOf(key, data))
        }
    }


    private class KeyObserver(val key: String, val observer: Observer) : Observer {
        override fun update(o: Observable, arg: Any) {
            val data = arg as? Array<*> ?: return
            if ((data[0] as String) == key) {
                observer.update(o, data[1])
            }
        }
    }

    companion object {
        // im消息的key
        const val KEY_IM_MESSAGE = "KEY_IM_MESSAGE"
    }
}