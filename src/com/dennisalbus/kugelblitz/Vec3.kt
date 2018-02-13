package com.dennisalbus.kugelblitz

import kotlin.math.pow
import kotlin.math.sqrt

class Vec3(var x: Double = 0.0,
           var y: Double = 0.0,
           var z: Double = 0.0) {

    override fun toString(): String = "Vec3(x=$x, y=$y, z=$z)"

    operator fun unaryPlus() = this
    operator fun unaryMinus() = Vec3(-this.x, -this.y, this.z)
    operator fun plus(rhs: Vec3) = Vec3(this.x + rhs.x, this.y + rhs.y, this.z + rhs.z)
    operator fun minus(rhs: Vec3) = Vec3(this.x - rhs.x, this.y - rhs.y, this.z - rhs.z)

    operator fun times(rhs: Vec3) = Vec3(this.x * rhs.x, this.y * rhs.y, this.z * rhs.z)
    operator fun times(rhs: Double) = Vec3(this.x * rhs, this.y * rhs, this.z * rhs)
    operator fun timesAssign(rhs: Vec3) {
        this.x = this.x * rhs.x
        this.y = this.y * rhs.y
        this.z = this.z * rhs.z
    }

    operator fun timesAssign(rhs: Double) {
        this.x = this.x * rhs
        this.y = this.y * rhs
        this.z = this.z * rhs
    }

    operator fun div(rhs: Vec3) = Vec3(this.x / rhs.x, this.y / rhs.y, this.z / rhs.z)
    operator fun div(rhs: Double) = Vec3(this.x / rhs, this.y / rhs, this.z / rhs)
    operator fun divAssign(rhs: Vec3) {
        this.x = this.x / rhs.x
        this.y = this.y / rhs.y
        this.z = this.z / rhs.z
    }

    operator fun divAssign(rhs: Double) {
        this.x = this.x / rhs
        this.y = this.y / rhs
        this.z = this.z / rhs
    }

    operator fun get(index: Int): Double {
        return when (index) {
            0 -> this.x
            1 -> this.y
            2 -> this.z
            else -> throw IndexOutOfBoundsException("Vec3 class has only 3 values (x, y, z).")
        }
    }

    fun length() = sqrt(this.x.pow(2) + this.y.pow(2) + this.z.pow(2))
    fun length2() = this.x.pow(2) + this.y.pow(2) + this.z.pow(2)
    fun normalize() {
        this /= this.length()
    }

    fun normalized() = this / this.length()
    fun dot(other: Vec3) = Vec3(this.x * other.x + this.y * other.y + this.z * other.z)
    fun cross(other: Vec3) = Vec3(this.y * other.z - this.z * other.y,
            -(this.x * other.z - this.z * other.x),
            this.x * other.y - this.y * other.x)
}

operator fun Double.times(rhs: Vec3) = Vec3(rhs.x * this, rhs.y * this, rhs.z * this)
fun lerp(a: Vec3, b: Vec3, mix: Double) = (1.0 - mix) * a + mix * b
