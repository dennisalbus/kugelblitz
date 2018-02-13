package com.dennisalbus.kugelblitz


fun main(args: Array<String>) {

    val xres = 600
    val yres = 400
    val buffer: Array<Vec3> = Array(xres * yres) { Vec3() }
    for (yy in 0 until yres) {
        for (xx in 0 until xres) {
            val offset = (yy * xres) + xx
            println(offset)
            buffer[offset].x = (xx.toDouble() / xres.toDouble()) * 255.0
            buffer[offset].y = (yy.toDouble() / yres.toDouble()) * 255.0
        }
    }
    val img = Image(xres, yres, buffer)
    img.write()
}