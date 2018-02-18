package com.dennisalbus.kugelblitz

fun hit_sphere(center: Vec3, radius: Double, ray: Ray): Boolean {
    val oc = ray.origin - center
    val a = dot(ray.direction, ray.direction)
    val b = 2.0 * dot(oc, ray.direction)
    val c = dot(oc, oc) - radius * radius
    val discriminant = b * b - 4.0 * a * c
    return (discriminant > 0)
}

fun color(ray: Ray): Vec3 {
    return if (hit_sphere(Vec3(0.0, 0.0, -1.0), 0.5, ray)) {
        Vec3(1.0, 0.0, 0.0)
    } else {
        background(ray)
    }
}

fun background(ray: Ray): Vec3 {
    val unitDirection = ray.direction.normalized()
    val t = 0.5 * unitDirection.y + 1.0
    return lerp(Vec3(1.0, 1.0, 1.0), Vec3(0.5, 0.7, 1.0), t)
}

fun main(args: Array<String>) {
    val xres = 800
    val yres = 400

    val lowerLeftCorner = Vec3(-2.0, -1.0, -1.0)
    val horizontal = Vec3(4.0, 0.0, 0.0)
    val vertical = Vec3(0.0, 2.0, 0.0)
    val origin = Vec3()

    val buffer: Array<Vec3> = Array(xres * yres) { Vec3() }
    for (yy in 0 until yres) {
        for (xx in 0 until xres) {
            val u = xx.toDouble() / xres.toDouble()
            val v = yy.toDouble() / yres.toDouble()
            val ray = Ray(origin, (lowerLeftCorner + u * horizontal + v * vertical).normalized())
            val offset = (xres * yres - 1) - ((yy * xres) + xx)
            buffer[offset] = color(ray)
        }
    }
    val img = Image(xres, yres, buffer)
    img.write(gamma = 2.2)
}