package com.binarymonks.jj.core.components

import com.binarymonks.jj.core.copy
import com.binarymonks.jj.core.properties.PropOverride
import com.binarymonks.jj.core.things.Thing
import kotlin.reflect.KClass
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

private val propDelegateType = PropOverride::class.createType(listOf(KTypeProjection(null, null)))

abstract class Component {

    internal var parent: Thing? = null
        set(value) {
            field = value
            this::class.declaredMemberProperties.forEach {
                if (it.returnType.isSubtypeOf(propDelegateType)) {
                    val b = it.name
                    val pd = this.javaClass.kotlin.memberProperties.first { it.name == b }.get(this) as PropOverride<*>
                    pd.hasProps = value
                }
            }
        }

    fun myThing(): Thing {
        return checkNotNull(parent)
    }

    /**
     * This will be used to create a new instances of your component.
     *
     * Public fields of the component will be copied to the new instance.
     *
     * If it is not a primitive the reference will be shared by the clone unless it is [com.binarymonks.jj.core.Copyable], then copy will be called.
     *
     * If this is not sufficient then override this method.
     */
    open fun clone(): Component {
        return copy(this)
    }

    /**
     * Components are stored and retrieved by their type. It makes no sense to have more than
     * one component of the same type operating on a given [Thing]
     *
     * But you may have several implementations of a type. This lets you specify the key type (or top level interface) that
     * will be used to store and retrieve your [Component] if you need to.
     */
    open fun type(): KClass<Component> {
        return this::class as KClass<Component>
    }

    /**
     * This will be called when a new instance or a de-pooled instance is added to the game world as part of its
     * [Thing] - if your component is not stateless, and your [Thing] is pooled you should re-initialise that state.
     */
    open fun onAddToWorld() {
    }

    /**
     * This will be called on every game loop. Override this for ongoing tasks
     */
    open fun update() {

    }

    /**
     * This will be called when the [Thing] is removed from the game world to be pooled/disposed.
     *
     * It will also be called if your [Component] identifies itself as being [Component.isDone]
     *
     * Cleanup any references or state as appropriate.
     */
    open fun onRemoveFromWorld() {
    }

    /**
     * You can also build short lived components which can be applied to a [Thing] and then identify themselves as
     * done when they have completed their task. They will then be removed from the [Thing]
     */
    open fun isDone(): Boolean {
        return false
    }

}