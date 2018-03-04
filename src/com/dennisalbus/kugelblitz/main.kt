package com.dennisalbus.kugelblitz

import java.util.Random
import java.util.logging.Logger

data class Settings(val xres: Int = 800,
                    val yres: Int = 400,
                    val samples: Int = 16)

fun color(ray: Ray, world: HitableList, depth: Int = 0): Vec3 {
    val hitRecord = HitRecord()
    val scatterEvent = ScatterEvent()
    return if (world.hit(ray, 0.001, Double.MAX_VALUE, hitRecord)) {
        if (depth < 10 && hitRecord.material.scatter(ray, hitRecord, scatterEvent)) {
            scatterEvent.attenuation * color(scatterEvent.scatteredRay, world, depth + 1)
        } else {
            Vec3()
        }
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
    val Settings = Settings(800, 400, 16)

    val LOG = Logger.getLogger("kugelblitz.main")
    val logsteps = Array<Int>(101) { (it * (Settings.yres - 1) / 100.0).toInt() }

    val rand = Random()

    val world = HitableList(
            arrayOf(
                    Sphere(Vec3(0.0, -100.5, -1.0), 100.0, Lambertian(Vec3(0.8, 0.8, 0.3))),

                    Sphere(Vec3(0.0, 0.0, -1.0), 0.5, Lambertian(Vec3(0.8, 0.3, 0.3))),
                    Sphere(Vec3(1.0, 0.0, -1.0), 0.5, Metal(Vec3(0.8, 0.6, 0.2), 0.8)),
                    Sphere(Vec3(-1.0, 0.0, -1.0), 0.5, Metal(Vec3(0.8, 0.8, 0.8), 0.3))
            )
    )

    val cam = Camera()

    val buffer = ArrayList<Vec3>()
    for (yy in 0 until Settings.yres) {
        val linebuffer: Array<Vec3> = Array(Settings.xres) { Vec3() }
        for (xx in 0 until Settings.xres) {
            var col = Vec3()
            for (s in 0 until Settings.samples) {
                val u = (xx.toDouble() + rand.nextDouble()) / Settings.xres.toDouble()
                val v = (yy.toDouble() + rand.nextDouble()) / Settings.yres.toDouble()
                val ray = cam.makeRay(u, v)
                col += color(ray, world)
                /* automatically weight the samples during rendering
                 * so we can easily do progressive rendering
                 */
            }
            linebuffer[xx] = col / Settings.samples.toDouble()
        }
        if (yy in logsteps) {
            LOG.info("Process: ${logsteps.indexOf(yy)}%")
        }
        buffer.addAll(linebuffer)
    }
    val img = Image(Settings.xres, Settings.yres, buffer.toTypedArray())
    img.write(gamma = 2.2)
}