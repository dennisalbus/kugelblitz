package com.dennisalbus.kugelblitz

import java.util.Random

fun color(ray: Ray, world: HitableList, depth: Int = 0): Vec3 {
    val hitRecord = HitRecord()
    val scatterEvent = ScatterEvent()
    if (world.hit(ray, 0.001, Double.MAX_VALUE, hitRecord)) {
        if (depth < 10 && hitRecord.material.scatter(ray, hitRecord, scatterEvent)) {
            return scatterEvent.attenuation * color(scatterEvent.scatteredRay, world, depth + 1)
        } else {
            return Vec3()
        }
    } else {
        return background(ray)
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
        val samples = 16
        val rand = Random()

        val world = HitableList(
                arrayOf(
                        Sphere(Vec3(0.0, 0.0, -1.0), 0.5, Lambertian(Vec3(0.5, 0.8, 0.1))),
                        Sphere(Vec3(0.0, -100.5, -1.0), 100.0, Lambertian(Vec3(0.1, 0.4, 0.8)))
                )
        )

        val cam = Camera()

        val buffer = ArrayList<Vec3>()
        for (yy in 0 until yres) {
            val linebuffer: Array<Vec3> = Array(xres) { Vec3() }
            for (xx in 0 until xres) {
                var col = Vec3()
                for (s in 0 until samples) {
                    val u = (xx.toDouble() + rand.nextDouble()) / xres.toDouble()
                    val v = (yy.toDouble() + rand.nextDouble()) / yres.toDouble()
                    val ray = cam.makeRay(u, v)
                    col += color(ray, world)
                    /* automatically weight the samples during rendering
                     * so we can easily do progressive rendering
                     */
                }
                linebuffer[xx] = col / samples.toDouble()
            }
            buffer.addAll(linebuffer)
        }
        val img = Image(xres, yres, buffer.toTypedArray())
        img.write(gamma = 2.2)
    }