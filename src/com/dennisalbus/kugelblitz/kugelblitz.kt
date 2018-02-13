package com.dennisalbus.kugelblitz

fun color(r: Ray): Vec3 {
    val unitDirection = r.direction.normalized()
    val t = 0.5 * unitDirection.y + 1.0
    return lerp(Vec3(1.0, 1.0, 1.0), Vec3(0.5, 0.7, 1.0), t)
}

fun main(args: Array<String>) {
    val xres = 200
    val yres = 100

    val lowerLeftCorner = Vec3(-2.0, -1.0, -1.0)
    val horizontal = Vec3(4.0, 0.0, 0.0)
    val vertical = Vec3(0.0, 2.0, 0.0)
    val origin = Vec3()

    val buffer: Array<Vec3> = Array(xres * yres) { Vec3() }
    for (yy in 0 until yres) {
        for (xx in 0 until xres) {
            val u = xx.toDouble() / xres.toDouble()
            val v = yy.toDouble() / yres.toDouble()
            val ray = Ray(origin, lowerLeftCorner + u * horizontal + v * vertical)
            val offset = (xres * yres - 1) - ((yy * xres) + xx)
            buffer[offset] = color(ray) * 255.0
        }
    }
    val img = Image(xres, yres, buffer)
    img.write()
}