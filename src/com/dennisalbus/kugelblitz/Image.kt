package com.dennisalbus.kugelblitz

import java.io.File
import kotlin.math.roundToInt

class Image(private var xres: Int = 600,
            private var yres: Int = 400) {

    fun write(file: String ="/Users/dalbus/Code/kugelblitz/images/test.ppm") {
        File(file).bufferedWriter().use {
            it.write("P3")
            it.newLine()
            it.write("$xres $yres")
            it.newLine()
            it.write("255")
            it.newLine()

            for(yy in 0 until yres) {
                for(xx in 0 until xres) {
                    val r = ((xx.toFloat() / xres.toFloat()) * 255f).roundToInt()
                    val g = ((yy.toFloat() / yres.toFloat()) * 255f).roundToInt()
                    it.write("$r $g 0 ")
                    it.newLine()
                }
            }
        }
    }
}