package com.dennisalbus.kugelblitz

import kotlin.math.sqrt

class Sphere(val center: Vec3, val radius: Double) : Hitable {
    override fun hit(ray: Ray, tMin: Double, tMax: Double, hitRecord: HitRecord): Boolean {
        val oc = ray.origin - center
        val a = dot(ray.direction, ray.direction)
        val b = 2.0 * dot(oc, ray.direction)
        val c = dot(oc, oc) - radius * radius
        val discriminant = b * b - 4.0 * a * c

        if (discriminant > 0) {
            var t = (-b - sqrt(discriminant)) / (2.0 * a)
            if (t < tMax && t > tMin) {
                hitRecord.t = t
                hitRecord.hitp = ray.point_at_parameter(t)
                hitRecord.normal = (hitRecord.hitp - center).normalized()
                return true
            }
            t = (-b + sqrt(discriminant)) / (2.0 * a)
            if (t < tMax && t > tMin) {
                hitRecord.t = t
                hitRecord.hitp = ray.point_at_parameter(t)
                hitRecord.normal = (hitRecord.hitp - center).normalized()
                return true
            }
        }
        return false
    }
}