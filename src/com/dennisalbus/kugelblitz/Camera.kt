package com.dennisalbus.kugelblitz

class Camera(val topLeftCorner: Vec3 = Vec3(-2.0, 1.0, -1.0),
             val horizontal: Vec3 = Vec3(4.0, 0.0, 0.0),
             val vertical: Vec3 = Vec3(0.0, 2.0, 0.0),
             val origin: Vec3 = Vec3()) {

    fun make_ray(u: Double, v: Double): Ray = Ray(origin, (topLeftCorner + u * horizontal - v * vertical).normalized())
}
