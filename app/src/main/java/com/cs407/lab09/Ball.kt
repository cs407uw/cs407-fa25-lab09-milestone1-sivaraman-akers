package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if (isFirstUpdate) {
            accX = xAcc
            accY = yAcc
            isFirstUpdate = false
            return
        }

        if (dT <= 0f) return

        // Save previous acceleration
        val a0x = accX
        val a0y = accY

        // New acceleration becomes "a1"
        val a1x = xAcc
        val a1y = yAcc

        // --- Physics equations from handout ---
        // v1 = v0 + 0.5 * (a0 + a1) * dt
        val newVelX = velocityX + 0.5f * (a0x + a1x) * dT
        val newVelY = velocityY + 0.5f * (a0y + a1y) * dT

        // l = v0 * dt + (1/6) * (3a0 + a1) * dt^2
        val speedFactor = 500f  // adjust experimentally
        val dx = (velocityX * dT + (1f/6f) * (3f * a0x + a1x) * dT * dT) * speedFactor
        val dy = (velocityY * dT + (1f/6f) * (3f * a0y + a1y) * dT * dT) * speedFactor


        // Update position
        posX += dx
        posY += dy

        // Update velocity
        velocityX = newVelX
        velocityY = newVelY

        // Store new accelerations for next step
        accX = a1x
        accY = a1y

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        val r = ballSize / 2f

        // Left boundary
        if (posX < r) {
            posX = r
            velocityX = 0f
            accX = 0f
        }

        // Right boundary
        if (posX > backgroundWidth - r) {
            posX = backgroundWidth - r
            velocityX = 0f
            accX = 0f
        }

        // Top boundary
        if (posY < r) {
            posY = r
            velocityY = 0f
            accY = 0f
        }

        // Bottom boundary
        if (posY > backgroundHeight - r) {
            posY = backgroundHeight - r
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        // Center of the screen
        posX = backgroundWidth / 2f - ballSize / 2f
        posY = backgroundHeight / 2f - ballSize / 2f


        // Zero velocities + accelerations
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f

        // Next update will act like first update again
        isFirstUpdate = true
    }
}