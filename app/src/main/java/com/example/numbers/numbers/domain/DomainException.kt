package com.example.numbers.numbers.domain

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException : DomainException()