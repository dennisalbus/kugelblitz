package com.dennisalbus.kugelblitz

import java.io.File
import kotlin.math.roundToInt

class Image(private val xres: Int,
            private val yres: Int,
            private val buffer: Array<Vec3>) {

    init {
        if (buffer.size != xres * yres) {
            throw Exception("Buffer is of wrong size")
        }
    }

    fun write(file: String = "/Users/dalbus/Code/kugelblitz/images/test.ppm", gamma: Double = 2.2) {
        File(file).bufferedWriter().use {
            it.write("P3\n$xres $yres\n255\n")
            for (pixel in buffer) {
                it.write("${pixel.x.roundToInt()} ${pixel.y.roundToInt()} ${pixel.z.roundToInt()}\n")
            }
        }
    }
}