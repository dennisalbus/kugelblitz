package com.dennisalbus.kugelblitz

class HitableList(val hitables: Array<Hitable>) : Hitable {
    override fun hit(ray: Ray, tMin: Double, tMax: Double, hitRecord: HitRecord): Boolean {
        val temp_record = HitRecord()
        var hit = false
        var closest = tMax
        for (hitable in hitables) {
            if (hitable.hit(ray, tMin, closest, temp_record)) {
                hit = true
                closest = temp_record.t
                hitRecord.t = temp_record.t
                hitRecord.normal = temp_record.normal
                hitRecord.hitp = temp_record.hitp
            }
        }
        return hit
    }
}