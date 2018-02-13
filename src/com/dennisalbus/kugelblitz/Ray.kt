package com.dennisalbus.kugelblitz

import kotlin.math.absoluteValue


class Ray(val origin: Vec3,
          val direction: Vec3) {

    init {
        val epsilon = 0.001
        if ((direction.length() - 1.0).absoluteValue > epsilon) {
            throw Exception("Length of the direction vector must be 1.0. Consider normalizing the Vector first.")
        }
    }

    fun point_at_parameter(t: Double): Vec3 = origin + t * direction

}