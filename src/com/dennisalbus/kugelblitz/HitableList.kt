package com.dennisalbus.kugelblitz

class HitableList(val hitables: Array<Hitable>) : Hitable {
    override fun hit(ray: Ray, tMin: Double, tMax: Double, hitRecord: HitRecord): Boolean {
        val tempRecord = HitRecord()
        var hit = false
        var closest = tMax
        for (hitable in hitables) {
            if (hitable.hit(ray, tMin, closest, tempRecord)) {
                hit = true
                closest = tempRecord.t
                hitRecord.t = tempRecord.t
                hitRecord.normal = tempRecord.normal
                hitRecord.hitp = tempRecord.hitp
                hitRecord.material = tempRecord.material
            }
        }
        return hit
    }
}