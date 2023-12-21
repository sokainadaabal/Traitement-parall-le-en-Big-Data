package org.sid.coreapi.events

import kotlinx.serialization.Serializable

abstract class BaseEvent<T>(
        open val id:T
)


@Serializable
data class  CustomerCreatedEvent(
        override  val id:String,
        val name:String,
        val email: String
):BaseEvent<String>(id)

data class  CustomerUpdatedEvent(
        override  val id:String,
        val name:String,
        val email: String
):BaseEvent<String>(id)