package com.binarymonks.jj.core.components

import com.binarymonks.jj.core.properties.PropOverride
import com.binarymonks.jj.core.things.Thing
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito


class ComponentTest {

    @Test
    fun clone() {

        val original = PrimitiveFields("name1", 2)
        original.copyable.set("blue")
        original.hiddenName = "altered"
        original.parent = Mockito.mock(Thing::class.java)

        val copy: PrimitiveFields = original.clone() as PrimitiveFields

        Assert.assertNull(copy.parent)
        Assert.assertNotSame(original, copy)
        Assert.assertEquals(original.name, copy.name)
        Assert.assertEquals(original.number, copy.number)
        Assert.assertNotEquals(original.hiddenName, copy.hiddenName)
        Assert.assertNotSame(original.copyable, copy.copyable)
        Assert.assertEquals(original.copyable, copy.copyable)
    }
}


class PrimitiveFields(
        var name: String? = null,
        var number: Int = 0,
        var copyable: PropOverride<String> = PropOverride("nothing")
) : Component() {
    internal var hiddenName: String = "hidden"
}

