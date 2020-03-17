package de.j4velin.ledclient

import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

private val okHttp = OkHttpClient()
private val mediaType = "application/json; charset=utf-8".toMediaType()

fun trigger(effect: LedEffect) {
    Thread {

        Log.i(TAG, "Payload: ${effect.toJSON()}")

        val body = effect.toJSON().toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url("http://192.168.178.23:5000/effect/" + effect.name)
            .post(body)
            .build()

        okHttp.newCall(request).execute().use { response ->
            if (!response.isSuccessful) Log.e(TAG, "Exception triggering effect: $response")
        }
    }.start()
}