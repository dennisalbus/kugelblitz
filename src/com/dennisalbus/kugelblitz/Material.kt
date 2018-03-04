package com.dennisalbus.kugelblitz

import java.util.Random

data class ScatterEvent(var attenuation: Vec3 = Vec3(),
                        var scatteredRay: Ray = Ray(Vec3(), Vec3(1.0, 0.0, 0.0)))

interface Material {
    fun scatter(rayIn: Ray, hitRecord: HitRecord, scatterEvent: ScatterEvent): Boolean
}

class Lambertian(val albedo: Vec3) : Material {
    override fun scatter(rayIn: Ray, hitRecord: HitRecord, scatterEvent: ScatterEvent): Boolean {
        val target = (hitRecord.normal + randomInUnitSphere()).normalized()
        scatterEvent.scatteredRay = Ray(hitRecord.hitp, target)
        scatterEvent.attenuation = albedo
        return true
    }
}

class Metal(val albedo: Vec3, val roughness: Double = 0.0) : Material {
    override fun scatter(rayIn: Ray, hitRecord: HitRecord, scatterEvent: ScatterEvent): Boolean {
        val reflected = reflect(rayIn.direction.normalized(), hitRecord.normal)
        scatterEvent.scatteredRay = Ray(hitRecord.hitp, (reflected + roughness * randomInUnitSphere()).normalized())
        scatterEvent.attenuation = albedo
        return reflected dot hitRecord.normal > 0
    }
}

fun randomInUnitSphere(): Vec3 {
    var P: Vec3
    val rand = Random()
    do {
        P = 2.0 * Vec3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()) - Vec3(1.0, 1.0, 1.0)
    } while (P.length2() >= 1.0)
    return P
}

fun reflect(v: Vec3, n: Vec3): Vec3 = v - (2.0 * v dot n) * n
