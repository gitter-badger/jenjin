package com.binarymonks.jj.demo

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.binarymonks.jj.core.Game
import com.binarymonks.jj.core.JJ
import com.binarymonks.jj.core.JJConfig
import com.binarymonks.jj.core.specs.Rectangle
import com.binarymonks.jj.core.specs.builders.*


class D06_lights : Game(MyConfig06.jjConfig) {

    override fun gameOn() {


        JJ.assets.loadNow("textures/circuit_background.png", Texture::class)

        JJ.render.setAmbientLight(0f, 0f, 0f, 0.4f)

        JJ.scenes.instantiate(scene {
            node {
                thing {
                    render { imageTexture("textures/circuit_background.png") { width = 20f; height = 40f } }
                }
            }
            node(params { x = -5f; y = 5f }) {
                thing {
                    physics { fixture { shape = Rectangle(2.5f, 2.5f) } }
                    render { rectangleRender(2.5f, 2.5f) { color.set(Color.BLUE) } }
                }
            }
            node(params { x = 5f; y = -5f }) {
                thing {
                    physics { fixture { shape = Rectangle(2.5f, 2.5f) } }
                    render { rectangleRender(2.5f, 2.5f) { color.set(Color.BLUE) } }
                }
            }
            node {
                thing {
                    physics {
                        fixture { shape = Rectangle(1.5f, 1.5f) }
                        pointLight {
                            reach = 30f
                        }
                    }
                }
            }
        })
    }
}

object MyConfig06 {
    var jjConfig: JJConfig = JJConfig()

    init {
        jjConfig.b2dConfig.debug = true
        jjConfig.b2dConfig.gravity = Vector2()

        jjConfig.gameViewConfig.worldBoxWidth = 20f
        jjConfig.gameViewConfig.cameraPosX = 0f
        jjConfig.gameViewConfig.cameraPosY = 0f
    }
}