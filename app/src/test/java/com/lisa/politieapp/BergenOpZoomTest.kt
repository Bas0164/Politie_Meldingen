package com.lisa.politieapp

import org.junit.Test
import org.junit.Assert.*


class BergenOpZoomTest {
    @Test
    fun testStringLength() {
        val text = "Bergen op Zoom"
        val expectedLength = 14 // De verwachte lengte van de tekst "Bergen op Zoom"
        assertEquals(expectedLength, text.length) // Controleer of de lengte van de tekst gelijk is aan de verwachte lengte
    }
    @Test
    fun testEndsWith() {
        val text = "Bergen op Zoom"
        assertTrue(text.endsWith("Zoom")) // Controleer of de tekst eindigt op "Zoom"
    }

    @Test
    fun testContains() {
        val text = "Bergen op Zoom is gezellig!"
        assertTrue(text.contains("gezellig")) // Controleer of de tekst "gezellig" bevat
    }

    @Test
    fun testReplace() {
        val text = "Bergen op Zoom"
        val replacedText = text.replace("Bergen op Zoom", "Krabbegat") // Vervang "Bergen op Zoom" door "Krabbegat" in de tekst
        assertEquals("Krabbegat", replacedText) // Controleer of de vervangen tekst gelijk is aan "Krabbegat"
    }

    @Test
    fun testBergenOpZoomIsBetterThanRoosendaal() {
        assertTrue(BergenOpZoomIsCozierThanRoosendaal()) // Controleer of Bergen op Zoom gezelliger is dan Roosendaal
    }

    private fun BergenOpZoomIsCozierThanRoosendaal(): Boolean {
        // Controleert of Bergen op Zoom een gezelliger is dan roosendaal
        // Return true als Bergen op Zoom gezeligger is, anders false
        return true
    }


}