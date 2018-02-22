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

    fun write(file: String = "/Users/dalbus/Code/kugelblitz/images/test.ppm", gamma: Double = 1.0) {
        File(file).bufferedWriter().use { writer ->
            writer.write("P3\n$xres $yres\n255\n")
            buffer.map { it.pow(1 / gamma) * 255.0 }
                    .forEach {
                        if (it.x.isNaN() or it.y.isNaN() or it.z.isNaN()) {
                            val const = 255
                            writer.write("$255 $const $const\n")
                        } else {
                            writer.write("${it.x.roundToInt()} ${it.y.roundToInt()} ${it.z.roundToInt()}\n")
                        }
                    }
        }
    }
}