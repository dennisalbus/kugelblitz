package com.dennisalbus.kugelblitz

data class HitRecord(var t: Double = 0.0,
                     var hitp: Vec3 = Vec3(),
                     var normal: Vec3 = Vec3(),
                     var hit: Boolean = false)

interface Hitable {
    fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord
}