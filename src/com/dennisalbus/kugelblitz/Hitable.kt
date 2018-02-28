package com.dennisalbus.kugelblitz

data class HitRecord(var t: Double = 0.0,
                     var hitp: Vec3 = Vec3(),
                     var normal: Vec3 = Vec3(),
                     var material: Material = Lambertian(Vec3(0.5, 0.5, 0.5)))

interface Hitable {
    fun hit(ray: Ray, tMin: Double, tMax: Double, hitRecord: HitRecord): Boolean
}