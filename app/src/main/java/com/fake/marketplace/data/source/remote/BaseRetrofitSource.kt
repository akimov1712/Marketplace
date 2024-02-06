package com.fake.marketplace.data.source.remote

import com.fake.marketplace.data.BackendException
import com.fake.marketplace.data.ParseBackendResponseException
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

open class BaseRetrofitSource {

    suspend fun <T>wrapRetrofitExceptions(block: suspend() -> T): T{
        return try {
            block()
        } catch (e: JsonIOException){
            throw ParseBackendResponseException()
        } catch (e: JsonParseException){
            throw ParseBackendResponseException()
        } catch (e: HttpException){
            throw createBackendException(e)
        } catch (e: IOException){
            throw ConnectException()
        }
    }

    private fun createBackendException(e: HttpException): Exception{
        return try {
            BackendException(
                name = e.message ?: "undefined",
                code = e.code()
            )
        } catch (e: Exception){
            throw ParseBackendResponseException()
        }
    }

}