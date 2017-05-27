package com.binarymonks.jj.core

import com.binarymonks.jj.core.api.LayersAPI
import com.binarymonks.jj.core.api.PoolsAPI
import com.binarymonks.jj.core.api.ScenesAPI
import com.binarymonks.jj.core.api.ClockAPI
import com.binarymonks.jj.core.assets.Assets
import com.binarymonks.jj.core.audio.Audio
import com.binarymonks.jj.core.layers.GameRenderingLayer
import com.binarymonks.jj.core.layers.LayerStack
import com.binarymonks.jj.core.physics.PhysicsWorld
import com.binarymonks.jj.core.pools.Pools
import com.binarymonks.jj.core.render.RenderWorld
import com.binarymonks.jj.core.scenes.Scenes
import com.binarymonks.jj.core.time.ClockControls

/**
 * The front end global api.
 *
 * Provides access to the commonly used interfaces and operations for interacting
 * with the engine. For complete interfaces have a look at [JJ.B]
 *
 */
object JJ {
    lateinit var scenes: ScenesAPI
    lateinit var layers: LayersAPI
    lateinit var pools: PoolsAPI
    lateinit var clock: ClockAPI

    lateinit var B: Backend

    fun initialise(config: JJConfig) {
        B = Backend()
        B.config = config
        B.clock = ClockControls()
        B.scenes = Scenes()
        B.layers = LayerStack()
        B.physicsWorld = PhysicsWorld()
        B.renderWorld = RenderWorld()
        B.pools = Pools()
        B.assets = Assets()
        B.audio = Audio()
        B.layers.addLayerTop(GameRenderingLayer(config.gameViewConfig))

        scenes = B.scenes
        layers = B.layers
        pools = B.pools
        clock = B.clock
    }
}