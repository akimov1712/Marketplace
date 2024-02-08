package com.fake.marketplace.data

open class AppException : RuntimeException()


class CachedDataException : AppException()
class ParseBackendResponseException : AppException()
class BackendException(
    val name: String,
    val code: Int
) : AppException()
