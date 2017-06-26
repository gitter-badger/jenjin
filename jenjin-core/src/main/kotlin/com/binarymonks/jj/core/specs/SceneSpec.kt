package com.binarymonks.jj.core.specs

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ObjectMap
import com.binarymonks.jj.core.Copyable
import com.binarymonks.jj.core.JJ
import com.binarymonks.jj.core.assets.AssetReference
import com.binarymonks.jj.core.audio.SoundParams
import com.binarymonks.jj.core.components.Component
import com.binarymonks.jj.core.specs.physics.JointSpec
import com.binarymonks.jj.core.specs.physics.PhysicsSpec
import com.binarymonks.jj.core.specs.render.RenderSpec

internal var sceneIDCounter = 0

open class SceneSpec : SceneSpecRef {

    var id = sceneIDCounter++
    var name: String? = null
    internal var nodeCounter = 0
    var physics: PhysicsSpec = PhysicsSpec()
    var render: RenderSpec = RenderSpec()
    var sounds: Array<SoundParams> = Array()
    var components: Array<Component> = Array()
    internal var isPooled: Boolean = true
    var nodes: ObjectMap<String, SceneNode> = ObjectMap()
    var joints: Array<JointSpec> = Array()


    /**
     * Add a addNode [SceneSpecRef] instance
     *
     * @param scene The scene to instantiate
     * @param instanceParams The instance specific parameters
     */
    fun addNode(scene: SceneSpecRef, instanceParams: InstanceParams = InstanceParams.new()) {
        nodes.put(getName(instanceParams), SceneNode(scene, instanceParams))
    }

    /**
     * Add a addNode [SceneSpec] instance
     *
     * @param scenePath The path to the addNode[SceneSpec].
     * @param instanceParams The instance specific parameters
     */
    fun addNode(scenePath: String, instanceParams: InstanceParams = InstanceParams.new()) {
        nodes.put(getName(instanceParams), SceneNode(SceneSpecRefPath(scenePath), instanceParams))
    }

    private fun getName(instanceParams: InstanceParams): String? {
        return if (instanceParams.name == null) {
            "ANON${nodeCounter++}"
        } else {
            instanceParams.name
        }
    }

    override fun resolve(): SceneSpec {
        return this
    }

    override fun getAssets(): Array<AssetReference> {
        val assets: Array<AssetReference> = Array()
        //TODO: Make me specs not nullable - they always have one. In fact, things must become scenes
        for (node in render.renderNodes) {
            assets.addAll(node.getAssets())
        }
        sounds.forEach {
            it.soundPaths.forEach {
                assets.add(AssetReference(Sound::class, it))
            }
        }
        nodes.forEach { assets.addAll(it.value.sceneRef!!.getAssets()) }
        return assets
    }

}

class SceneNode(
        val sceneRef: SceneSpecRef? = null,
        val instanceParams: InstanceParams = InstanceParams.new()
)

interface SceneSpecRef {
    fun resolve(): SceneSpec
    fun getAssets(): Array<AssetReference>
}

fun sceneRef(path: String): SceneSpecRef {
    return SceneSpecRefPath(path)
}

class SceneSpecRefPath(val path: String) : SceneSpecRef {

    override fun getAssets(): Array<AssetReference> {
        return JJ.B.scenes.getScene(path).getAssets()
    }

    override fun resolve(): SceneSpec {
        return JJ.B.scenes.getScene(path)
    }
}