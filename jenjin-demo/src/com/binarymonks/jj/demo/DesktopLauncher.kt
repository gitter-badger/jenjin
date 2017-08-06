package com.binarymonks.jj.demo

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.binarymonks.jj.demo.demos.D01_nested_transforms
import com.binarymonks.jj.demo.demos.D06_lights_and_touch
import com.binarymonks.jj.demo.demos.D14_spine_bounding_boxes
import com.binarymonks.jj.demo.demos.D15_particles

object DesktopLauncher {
    @JvmStatic fun main(arg: Array<String>) {
        val lwjglConfig = LwjglApplicationConfiguration()
        lwjglConfig.height = 1000
        lwjglConfig.width = 1000

        //Swap out the various demo Games here
        LwjglApplication(D15_particles(), lwjglConfig)
    }
}
