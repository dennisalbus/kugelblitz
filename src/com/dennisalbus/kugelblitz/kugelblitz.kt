package com.dennisalbus.kugelblitz

fun color(ray: Ray, objects: HitableList): Vec3 {
    val hitRecord = HitRecord()
    return if (objects.hit(ray, 0.001, Double.MAX_VALUE, hitRecord)) {
        hitRecord.normal
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

    val topLeftCorner = Vec3(-2.0, 1.0, -1.0)
    val horizontal = Vec3(4.0, 0.0, 0.0)
    val vertical = Vec3(0.0, 2.0, 0.0)
    val origin = Vec3()

    val objects = HitableList(arrayOf(
            Sphere(Vec3(0.0, 0.0, -1.0), 0.5),
            Sphere(Vec3(0.0, -100.5, -1.0), 100.0)))

    val buffer = ArrayList<Vec3>()
    for (yy in 0 until yres) {
        val linebuffer: Array<Vec3> = Array(xres) { Vec3() }
        val v = yy.toDouble() / yres.toDouble()
        for (xx in 0 until xres) {
            val u = xx.toDouble() / xres.toDouble()
            val ray = Ray(origin, (topLeftCorner + u * horizontal - v * vertical).normalized())
            linebuffer[xx] = color(ray, objects)
        }
        buffer.addAll(linebuffer)
    }
    val img = Image(xres, yres, buffer.toTypedArray())
    img.write(gamma = 2.2)
}