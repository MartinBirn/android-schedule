package com.grsu.schedule_project.data.network.errorhandling

import com.grsu.schedule_project.data.model.dto.ErrorWrapperDto
import com.slack.eithernet.ApiException
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class ErrorConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return ResponseBodyConverter(getRawType(type))
    }

    class ResponseBodyConverter<T>(
        private val successClazz: Class<T>
    ) : Converter<ResponseBody, Any> {

        override fun convert(value: ResponseBody): T? {
            val moshi = Moshi.Builder().build()
            val response = value.string()
            try {
                val jsonAdapter = moshi.adapter(successClazz).failOnUnknown()
                return jsonAdapter.fromJson(response)
            } catch (e: JsonDataException) {
                val jsonAdapter = moshi.adapter(ErrorWrapperDto::class.java)
                val errorObject = jsonAdapter.fromJson(response)
                throw ApiException(errorObject?.error)
            }
        }
    }
}