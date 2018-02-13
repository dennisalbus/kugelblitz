package com.dennisalbus.kugelblitz

class Ray(val origin: Vec3,
          val direction: Vec3) {

    fun point_at_parameter(t: Double): Vec3 = origin + t * direction
}