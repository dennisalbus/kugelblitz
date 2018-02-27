package com.dennisalbus.kugelblitz

import java.util.Random

fun randomInUnitSphere(): Vec3 {
    var P: Vec3
    val rand = Random()
    do {
        P = 2.0 * Vec3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble()) - Vec3(1.0, 1.0, 1.0)
    } while (P.length2() >= 1.0)
    return P
}
